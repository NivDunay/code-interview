
function getProviders() {
    const jsonData = require('../../providers/providers');
    return JSON.parse(JSON.stringify(jsonData));
}

module.exports = {
    getProviders
};
