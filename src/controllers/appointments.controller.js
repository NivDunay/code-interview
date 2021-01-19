var createError = require('http-errors');
const {appointmentService} = require("../services")


function search(request, response, next){
    let query=request.query
    if(query.specialty===undefined||query.specialty===''||query.date===undefined||query.minScore===undefined){
        return next(createError(400));
    }
    if(isNaN(query.minScore)||isNaN(query.date)){
        return next(createError(400));
    }
    let result=appointmentService.search(query.specialty.toLocaleLowerCase(),Number(query.date),Number(query.minScore));

    return response.send(result);

}
function create(request, response, next){
    let body=request.body;
    if(body.date===undefined||body.name===undefined||body.name===''){
        return next(createError(400));
    }
    if(isNaN(body.date)){
        return next(createError(400));
    }
    let res = appointmentService.create(body.name,body.date);
    if(res){
        return response.send('ok');

    }else{
        return next(createError(400));
    }


}
module.exports = {
    search,
    create
};

