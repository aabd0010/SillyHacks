
var mysql = require('mysql');
var connection = mysql.createConnection({
    host: "xxxxxx.cit9ycp7tcxu.ap-southeast-2.rds.amazonaws.com",
    user: "xxxx",
    password: "xxxxxx",
    database: "xxxxx",
});

exports.handler = (event, context, callback) => {
    let ans_id = event.queryStringParameters.ansID;
    connection.query("SELECT * from ANSWERS where ans_id='" + ans_id + "'", function(error, results, fields) {
        if(error){
            // connection.destroy();
            throw error;
        }
        else if(results.length == 0){
            callback(error, "Answer not found");
        }
        else{
            // return the user object here
            let result = results[0];
            let returnObject = {
                ansID: result.ans_id,
                ansDesc: result.ans_desc,
                ansImage: result.ans_image,
                ansComment: result.ans_comment
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
