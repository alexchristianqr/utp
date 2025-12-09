import express from 'express';
import bodyParser from 'body-parser';
import { createConnection } from 'mysql2/promise';

const app = express();

app.use(bodyParser.json());

const port = 3000;

app.get('/', (req, res) => {
  res.send('API en nodejs - Desarrollado por Alex Christian!');
});

// Servir carpeta uploads como estática
app.use('/uploads', express.static(path.join(process.cwd(), 'uploads')));

const configDB = {
  host: 'localhost',
  port: 3306,
  user: 'root',
  password: '',
  database: 'db_productos',
};


/*
app.get('/productos', async (req, res) => {
  try {
    const cnx = await createConnection(configDB);
    const [rows] = await cnx.execute('SELECT * FROM productos');
    return res.json({ message: 'Todos los productos', data: rows });
  } catch (error) {
    return res.status(500).json({ error });
  }
});*/

import multer from 'multer';
import path from 'path';
import fs from 'fs';

// Crear carpeta uploads si no existe
const uploadDir = path.join(process.cwd(), 'uploads');
if (!fs.existsSync(uploadDir)) {
  fs.mkdirSync(uploadDir);
}

// Configuración de multer
const storage = multer.diskStorage({
  destination: function (req, file, cb) {
    cb(null, uploadDir);
  },
  filename: function (req, file, cb) {
    const uniqueSuffix = Date.now() + '-' + Math.round(Math.random() * 1e9);
    cb(null, uniqueSuffix + path.extname(file.originalname));
  },
});

const upload = multer({ storage: storage });


app.get('/', (req, res) => {
  res.send('API en Node.js - Servidor de Productos con Imágenes');
});



// Obtener todos los productos para la lista
app.get('/productos', async (req, res) => {
  try {
    const cnx = await createConnection(configDB);
    const [rows] = await cnx.execute('SELECT * FROM productos');
    await cnx.end();
    return res.json({ message: 'Todos los productos', data: rows });
  } catch (error) {
    return res.status(500).json({ error });
  }
});





/*
//ESTA API FUNCIONA PERO SE VA AGREGAR OTRO TEXTO PARA SER EDITADO
app.put('/productos/:id', upload.single('imagen'), async (req, res) => {
    try {
        const id = req.params.id;
        const { nombre, descripcion, precio, stock, categoria } = req.body;

        let imagenName = req.file ? `/uploads/${req.file.filename}` : null;

        const cnx = await createConnection(configDB); // ✅ Crear conexión

        const sql = `
          UPDATE productos 
          SET nombre=?, descripcion=?, precio=?, stock=?, categoria=?, imagen=IFNULL(?, imagen)
          WHERE id=?`;
        
        await cnx.execute(sql, [nombre, descripcion, precio, stock, categoria, imagenName, id]);

        const [updated] = await cnx.execute("SELECT * FROM productos WHERE id=?", [id]);

        await cnx.end(); // ✅ cerrar conexión

        res.json({ ok: true, data: updated[0] });
    } catch (e) {
        console.error(e);
        res.status(500).json({ ok: false, error: e.message });
    }
});*/


app.put('/productos/:id', upload.single('imagen'), async (req, res) => {
    const id = req.params.id;
    const { nombre, descripcion, precio, stock, categoria } = req.body;

    try {
        const cnx = await createConnection(configDB);

        // Nombre de la imagen si se subió
        const imagenName = req.file ? `/uploads/${req.file.filename}` : null;

        // Actualizar el producto
        const sqlUpdate = `
          UPDATE productos 
          SET nombre = ?, descripcion = ?, precio = ?, stock = ?, categoria = ?, imagen = IFNULL(?, imagen)
          WHERE id = ?`;

        await cnx.execute(sqlUpdate, [nombre, descripcion, precio, stock, categoria, imagenName, id]);

        // Obtener producto actualizado
        const [rows] = await cnx.execute("SELECT * FROM productos WHERE id = ?", [id]);

        const productoActualizado = rows.length ? rows[0] : null;

        // Respuesta: data siempre es lista + timestamp
        res.json({
            ok: true,
            data: productoActualizado ? [productoActualizado] : [],
            timestamp: new Date()  // nuevo dato adicional
        });

        await cnx.end();
    } catch (error) {
        console.error("Error al actualizar producto:", error);
        res.status(500).json({ ok: false, error: error.message });
    }
});




/* original
app.post('/productos', async (req, res) => {
  try {
    const { nombre, descripcion, precio, stock, categoria } = req.body;
    const cnx = await createConnection(configDB);
    const [result] = await cnx.execute(
      'INSERT INTO productos (nombre, descripcion, imagen, precio, stock, categoria) VALUES (?, ?, ?, ?, ?, ?)',
      [nombre, descripcion, imagen, precio, stock, categoria]
    );
    return res.json({
      message: 'producto registrado',
      data: { producto_id: result.insertId, nombre, descripcion, imagen, precio, stock, categoria },
    });
  } catch (error) {
    return res.status(500).json({ error });
  }
});*/

/* ORIGINAL 2
app.post('/productos', async (req, res) => {
  try {
    const { nombre, descripcion, precio, stock, categoria } = req.body;
    const imagen = req.body.imagen ?? null;  // ←🔥 AGREGADO

    const cnx = await createConnection(configDB);
    const [result] = await cnx.execute(
      'INSERT INTO productos (nombre, descripcion, imagen, precio, stock, categoria) VALUES (?, ?, ?, ?, ?, ?)',
      [nombre, descripcion, imagen, precio, stock, categoria]
    );

    return res.json({
      message: 'producto registrado',
      data: {
        producto_id: result.insertId,
        nombre,
        descripcion,
        imagen,
        precio,
        stock,
        categoria
      }
    });

  } catch (error) {
    return res.status(500).json({ error });
  }
});*/

app.post('/productos', upload.single('imagen'), async (req, res) => {
  try {
    const { nombre, descripcion, precio, stock, categoria } = req.body;
    const imagenUrl = req.file ? `/uploads/${req.file.filename}` : null; // URL relativa a guardar en DB

    const cnx = await createConnection(configDB);
    const [result] = await cnx.execute(
      'INSERT INTO productos (nombre, descripcion, imagen, precio, stock, categoria) VALUES (?, ?, ?, ?, ?, ?)',
      [nombre, descripcion, imagenUrl, precio, stock, categoria]
    );

    return res.json({
      message: 'Producto registrado',
      data: {
        producto_id: result.insertId,
        nombre,
        descripcion,
        imagen: imagenUrl,
        precio,
        stock,
        categoria,
      },
    });
  } catch (error) {
    console.error(error);
    res.status(500).json({ error });
  }
});

app.get('/productos/:id', async (req, res) => {
  const { id } = req.params;

  try {
    const cnx = await createConnection(configDB);

    const [rows] = await cnx.execute('SELECT id, nombre, stock FROM productos WHERE id = ?', [id]);

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
    const [rows] = await cnx.execute('SELECT stock FROM productos WHERE id = ?', [producto_id]);

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
    await cnx.execute('UPDATE productos SET stock = ? WHERE id = ?', [nuevoStock, producto_id]);

    // 5️⃣ Respuesta final
    res.json({
      message: 'Movimiento registrado y stock actualizado',
      data: {
        id: result.insertId,
        producto_id,
        tipo,
        cantidad,
        nuevoStock,
        descripcion: `Movimiento ${tipo}`,
      },
    });
  } catch (error) {
    console.error(error);
    res.status(500).json({ error: 'Error interno del servidor' });
  }
});

app.get('/movimientos/:producto_id', async (req, res) => {
  const { producto_id } = req.params;

  try {
    const cnx = await createConnection(configDB); // conexión directa

    // Verificar si el producto existe
    const [productoRows] = await cnx.execute('SELECT id, nombre FROM productos WHERE id = ?', [
      producto_id,
    ]);

    if (productoRows.length === 0) {
      return res.status(404).json({ message: 'Producto no encontrado' });
    }

    // Obtener movimientos
    const [movimientosRows] = await cnx.execute(
      'SELECT id, producto_id, tipo, cantidad, fecha, descripcion FROM movimientos WHERE producto_id = ? ORDER BY fecha DESC',
      [producto_id]
    );

    const entradas = movimientosRows.filter((m) => m.tipo === 'ENTRADA');
    const salidas = movimientosRows.filter((m) => m.tipo === 'SALIDA');

    res.json({
      message: 'Movimientos encontrados',
      data: {
        producto: productoRows[0],
        movimientos: movimientosRows,
        entradas,
        salidas,
      },
    });

    await cnx.end(); // cerrar la conexión
  } catch (error) {
    console.error('Error al consultar movimientos:', error);
    res.status(500).json({ error: 'Error interno del servidor al procesar la solicitud.' });
  }
});

app.listen(port, () => {
  console.log(`Server is running at http://localhost:${port}`);
});
