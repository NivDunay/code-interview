const appointmentRouter = require("./appointments.router");

module.exports = function(app){
    app.use('/appointments', appointmentRouter);
}
