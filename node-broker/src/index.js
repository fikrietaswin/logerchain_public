/**
 * @file This file is the entry point for the node-broker application.
 * @module index
 */

const express = require('express');
const routes = require('./routes');

/**
 * The port number for the server.
 * @type {number}
 */
const PORT = process.env.PORT || 3001;

/**
 * The Express application instance.
 * @type {express.Application}
 */
const app = express();

app.use(express.json());
app.use('/api', routes);

app.listen(PORT, () => {
  console.log(`Broker running on http://localhost:${PORT}`);
});
