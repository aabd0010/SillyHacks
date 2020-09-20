
var mysql = require('mysql');
var connection = mysql.createConnection({
    host: "xxxxx.cit9ycp7tcxu.ap-southeast-2.rds.amazonaws.com",
    user: "xxxxx",
    password: "xxxx",
    database: "xxxx",
});

exports.handler = (event, context, callback) => {
    let que_id = event.queryStringParameters.queID;
    connection.query("SELECT * from QUESTIONS where que_id='" + que_id + "'", function(error, results, fields) {
        if(error){
           // connection.destroy();
            throw error;
        }
        else if(results.length == 0){
            callback(error, "Question not found");
        }
        else{
            // return the user object here
            let result = results[0];
            let returnObject = {
                queID: result.que_id,
                queDesc: result.que_desc,
                queHint: result.que_hint
            }
            let response = {
                "isBase64Encoded": false,
                "statusCode": 200,
                "headers": {
                    'Access-Control-Allow-Origin': '*',
                    'Access-Control-Allow-Credentials': true,
                },
                "body": JSON.stringify(returnObject)
            }
            callback(error, response);
        }
        connection.end(function (err) { callback(err);});
    });
};
