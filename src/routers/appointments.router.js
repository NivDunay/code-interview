// npm packages
const express = require("express");

// app imports
const { appointmentsController } = require("../controllers");

// globals
const router = new express.Router();
const { search,create} = appointmentsController;

router
    .route("/")
    .get(search);

router
    .route("/")
    .post(create);



module.exports = router;
