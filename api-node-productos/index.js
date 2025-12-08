import express from 'express';
import bodyParser from 'body-parser';
import { createConnection } from 'mysql2/promise';

const app = express();

app.use(bodyParser.json());

const port = 3000;

app.get('/', (req, res) => {
  res.send('API en nodejs - Desarrollado por Alex Christian!');
});

const configDB = {
  host: 'localhost',
  port: 3306,
  user: 'root',
  password: '',
  database: 'db_productos',
};

app.get('/productos', async (req, res) => {
  try {
    const cnx = await createConnection(configDB);
    const [rows] = await cnx.execute('SELECT * FROM productos');
    return res.json({ message: 'Todos los productos', data: rows });
  } catch (error) {
    return res.status(500).json({ error });
  }
});

app.post('/productos', async (req, res) => {
  try {
    const { nombre, descripcion, precio, stock, categoria } = req.body;
    const cnx = await createConnection(configDB);
    const [result] = await cnx.execute(
      'INSERT INTO productos (nombre, descripcion, precio, stock, categoria) VALUES (?, ?, ?, ?, ?)',
      [nombre, descripcion, precio, stock, categoria]
    );
    return res.json({
      message: 'producto registrado',
      data: { producto_id: result.insertId, nombre, descripcion, precio, stock, categoria },
    });
  } catch (error) {
    return res.status(500).json({ error });
  }
});


app.get('/productos/:id', async (req, res) => {
  const { id } = req.params;

  try {
    const cnx = await createConnection(configDB);

    const [rows] = await cnx.execute(
      'SELECT id, nombre, stock FROM productos WHERE id = ?',
      [id]
    );

    if (rows.length === 0) {
      return res.status(404).json({ message: 'Producto no encontrado', data: [] });
    }

    res.json({ message: 'Producto encontrado', data: rows[0] });

  } catch (error) {
    res.status(500).json({ error });
  }
});







app.post('/movimientos', async (req, res) => {
  try {
    const { producto_id, tipo, cantidad } = req.body;

    if (!producto_id || !tipo || !cantidad) {
      return res.status(400).json({ message: 'Faltan datos obligatorios' });
    }

    const cnx = await createConnection(configDB);

    // 1️⃣ Registrar el movimiento
    const [result] = await cnx.execute(
      'INSERT INTO movimientos (producto_id, tipo, cantidad, descripcion) VALUES (?, ?, ?, ?)',
      [producto_id, tipo, cantidad, `Movimiento ${tipo}`]
    );

    // 2️⃣ Obtener el stock actual del producto
    const [rows] = await cnx.execute(
      'SELECT stock FROM productos WHERE id = ?',
      [producto_id]
    );

    if (rows.length === 0) {
      return res.status(404).json({ message: 'Producto no encontrado' });
    }

    let stockActual = rows[0].stock;

    // 3️⃣ Calcular nuevo stock según tipo de movimiento
    let nuevoStock;
    if (tipo === 'ENTRADA') {
      nuevoStock = stockActual + cantidad;
    } else if (tipo === 'SALIDA') {
      nuevoStock = stockActual - cantidad;
      if (nuevoStock < 0) nuevoStock = 0; // evitar stock negativo
    }

    // 4️⃣ Actualizar el stock del producto
    await cnx.execute(
      'UPDATE productos SET stock = ? WHERE id = ?',
      [nuevoStock, producto_id]
    );

    // 5️⃣ Respuesta final
    res.json({
      message: 'Movimiento registrado y stock actualizado',
      data: {
        id: result.insertId,
        producto_id,
        tipo,
        cantidad,
        nuevoStock,
        descripcion: `Movimiento ${tipo}`
      }
    });

  } catch (error) {
    console.error(error);
    res.status(500).json({ error: 'Error interno del servidor' });
  }
});





// Consultar movimientos por producto_id
app.get('/movimientos/:producto_id', async (req, res) => {
    const { producto_id } = req.params;
    let connection; // Declarado aquí para que sea accesible en el bloque finally

    try {
        // 1. Obtener una conexión del pool
        connection = await pool.getConnection();

        // 2. Verificar si el producto existe
        const [productoRows] = await connection.execute(
            'SELECT id, nombre FROM productos WHERE id = ?',
            [producto_id]
        );

        if (productoRows.length === 0) {
            // No se recomienda enviar data: [] si el producto no existe, 
            // solo el mensaje de error 404.
            return res.status(404).json({ message: 'Producto no encontrado' });
        }

        // 3. Obtener los movimientos del producto
        // ¡Cambiado! Se quitó la aserción de tipo TypeScript (as [Movimiento[], any])
        const [movimientosRows] = await connection.execute(
            'SELECT id, producto_id, tipo, cantidad, fecha, descripcion FROM movimientos WHERE producto_id = ? ORDER BY fecha DESC',
            [producto_id]
        ); 

        // 4. Separar en Entradas y Salidas
        // Usamos la variable 'm' sin tipado, lo cual es correcto en JavaScript puro.
        const entradas = movimientosRows.filter(m => m.tipo === 'ENTRADA');
        const salidas = movimientosRows.filter(m => m.tipo === 'SALIDA');

        // 5. Respuesta final
        res.json({
            message: 'Movimientos encontrados',
            data: {
                producto: productoRows[0],
                movimientos: movimientosRows,
                entradas,
                salidas
            }
        });

    } catch (error) {
        console.error("Error al consultar movimientos:", error);
        // Mejor manejar el error de forma genérica
        res.status(500).json({ error: 'Error interno del servidor al procesar la solicitud.' });
    } finally {
        // 6. Devolver la conexión al pool
        if (connection) connection.release(); 
    }
});






app.listen(port, () => {
  console.log(`Server is running at http://localhost:${port}`);
});
