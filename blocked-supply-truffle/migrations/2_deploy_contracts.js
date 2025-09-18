/**
 * @file This file is a Truffle migration script to deploy the ShipmentManagement smart contract.
 * @module 2_deploy_contracts
 */

const ShipmentManagement = artifacts.require("ShipmentManagement");

/**
 * Deploys the ShipmentManagement smart contract.
 * @param {Deployer} deployer - The Truffle deployer instance.
 */
module.exports = function (deployer) {
    deployer.deploy(ShipmentManagement);
};
