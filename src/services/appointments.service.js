let providersDict = undefined;
let providersDictByName = undefined;
let jsonData = undefined;
const {providersData} = require("../data")

function init() {
// console.log(jsonData)
     providersDict = {};
     providersDictByName = {};
    jsonData=providersData.getProviders();
    for(let i=0;i<jsonData.length;i++){
        let tempPro=jsonData[i];
        providersDictByName[[tempPro.name]]=tempPro;
        tempPro.specialties.forEach(sp=>{
            let sp_lower=sp.toLocaleLowerCase();
            if(!providersDict[sp_lower]){
                providersDict[sp_lower]=[];
            }
            providersDict[sp_lower].push(tempPro);
        });
    }
}

function search(specialty,date,minScore){
    checkData();
    let providers_res=[];
    if(providersDict[specialty]){
        let tempProviders=providersDict[specialty]
        tempProviders=tempProviders.filter(provider=>{
            return (provider.score>=minScore) && (provider.availableDates.some(t=> {
                return (t.from<=date)&&(t.to>=date)
                }
            ))
        });
        tempProviders=tempProviders.sort((a,b)=>b.score-a.score);
        providers_res=tempProviders.map(sp=>sp.name);
    }
return providers_res;
}
function create(name,date){
    checkData();
    if(providersDictByName[name]){
        let res=providersDictByName[name].availableDates.some(t=> {
            return (t.from<=date)&&(t.to>=date)
        });
        return res;
    }
    return false;
}
function checkData(){
    if(providersDict===undefined){
        init();
    }
}
module.exports = {
    search,
    create,
    init
};
