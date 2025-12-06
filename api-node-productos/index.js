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

app.listen(port, () => {
  console.log(`Server is running at http://localhost:${port}`);
});
