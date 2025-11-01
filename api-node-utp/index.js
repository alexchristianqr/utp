import express from "express";
import bodyParser from "body-parser";

const app = express();
const port = 3000;

app.get("/", (req, res) => {
  res.send("App en nodejs!");
});

app.get("/alumnos", (req, res) => {
  res.json({ data: [] });
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