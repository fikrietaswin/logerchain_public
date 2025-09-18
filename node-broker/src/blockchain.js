/**
 * @file This file initializes and exports the Web3 instance and the smart contract instance.
 * @module blockchain
 */

const Web3 = require('web3');
const contractJson = require('../abi/ShipmentManagement.json');
require('dotenv').config();

/**
 * The Web3 instance.
 * @type {Web3}
 */
const web3 = new Web3.default(process.env.BLOCKCHAIN_NODE_URL);

/**
 * The address of the smart contract.
 * @type {string}
 */
const contractAddress = process.env.CONTRACT_ADDRESS;

/**
 * The smart contract instance.
 * @type {Contract}
 */
const contract = new web3.eth.Contract(contractJson.abi, contractAddress);

module.exports = { web3, contract };
