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
  database: 'mydb',
};

app.get('/alumnos', async (req, res) => {
  try {
    const cnx = await createConnection(configDB);
    const [rows] = await cnx.execute('SELECT * FROM alumno');
    return res.json({ message: 'Todos los alumnos', data: rows });
  } catch (error) {
    return res.status(500).json({ error });
  }
});

app.get('/varones', async (req, res) => {
  try {
    const cnx = await createConnection(configDB);
    const [rows] = await cnx.execute('SELECT * FROM alumno WHERE alu_genero = "M"');
    return res.json({ message: 'Solo alumnos masculinos', data: rows });
  } catch (error) {
    return res.status(500).json({ error });
  }
});

app.get('/mujeres', async (req, res) => {
  try {
    const cnx = await createConnection(configDB);
    const [rows] = await cnx.execute('SELECT * FROM alumno WHERE alu_genero = "F"');
    return res.json({ message: 'Solo alumnos femeninos', data: rows });
  } catch (error) {
    return res.status(500).json({ error });
  }
});

app.get('/mayores', async (req, res) => {
  try {
    const cnx = await createConnection(configDB);
    const [rows] = await cnx.execute('SELECT * FROM alumno WHERE alu_edad > 18');
    return res.json({ message: 'Solo alumnos mayores de edad', data: rows });
  } catch (error) {
    return res.status(500).json({ error });
  }
});

app.listen(port, () => {
  console.log(`Server is running at http://localhost:${port}`);
});
