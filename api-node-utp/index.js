import express from "express";
import bodyParser from "body-parser";
import {createConnection} from "mysql2/promise";

const app = express();

app.use(bodyParser.json());

const port = 3000;

app.get("/", (req, res) => {
  res.send("API en nodejs - Desarrollado por Alex Christian!");
});

const config = {
  host: "localhost",
  port: 3306,
  user: "root",
  password: "",
  database: "mydb",
};

app.get("/alumnos", async (req, res) => {
  try {
    const cnx = await createConnection(config);
    // cnx.on("error", (err) => {
    //   console.error("DB error", err);
    // });
    const [rows] = await cnx.execute("SELECT * FROM alumno");
    return res.json({ data: rows });
  } catch (error) {
    return res.status(500).json({ error });
  }
});

app.get("/varones", (req, res) => {
  res.json({ data: [] });
});

app.get("/mujeres", (req, res) => {
  res.json({ data: [] });
});

app.get("/mayores", (req, res) => {
  res.json({ data: [] });
});

app.listen(port, () => {
  console.log(`Server is running at http://localhost:${port}`);
});