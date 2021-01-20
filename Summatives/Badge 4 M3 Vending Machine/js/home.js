$(document).ready(function () {
    loadItems();
    addQuarter();
    addDollar();
    addNickel();
    addDime();
    changeReturn();
    selectItem();
    vendItem();
});

function loadItems() {
    resetMoney();
    var itemsRow = $('#itemsRow');
    var itemNumber = 1;
    $.ajax({
        type: 'GET',
        url: 'http://tsg-vending.herokuapp.com/items',
        success: function (itemsArray) {
            $.each(itemsArray, function (index, item) {

                var itemName = item.name;
                var itemPrice = item.price;
                var itemQuantity = item.quantity;

                var itemId = item.id;

                var row = '<div onclick="selectItem(' + itemNumber + ', ' + itemId + ' )" class="col-md-3 border border-primary my-2">';
                row += '<p>' + itemNumber + '</p>';
                row += '<p class="text-center">' + itemName + '</p>';
                row += '<p class="text-center" type="number">' + itemPrice + '</p>';
                row += '<p class="text-center">Quantity Left: ' + itemQuantity + '</p>';
                row += '<p hidden>' + itemId + '</p>';
                row += '</div>';

                row += '<div class="col-md-1">';
                row += '</div>';


                itemsRow.append(row);
                itemNumber++;
            })
        },
        error: function () {
            $('#errorMessages')
                    .append($('<li>')
                            .attr({class: 'list-group-item list-group-item-danger'})
                            .text('Error calling web service. Please try again later.'));
        }
    });
}
function resetMoney() {
    var reset = 0.00;
    var floatReset = parseFloat(reset);
    $('#totalMoney').val(floatReset.toFixed(2));
}



function addQuarter() {
    $('#addQuarter').click(function () {

        var moneyField = $('#totalMoney');
        var totalMoney = $('#totalMoney').val();
        totalMoney = parseFloat(totalMoney) + 0.25;
        moneyField.val(totalMoney.toFixed(2));

    });
}
function addDollar() {
    $('#addDollar').click(function () {
        var moneyField = $('#totalMoney');
        var totalMoney = $('#totalMoney').val();
        totalMoney = parseFloat(totalMoney) + 1.00;
        moneyField.val(totalMoney.toFixed(2));

    });
}
function addNickel() {
    $('#addNickel').click(function () {
        var moneyField = $('#totalMoney');
        var totalMoney = $('#totalMoney').val();
        totalMoney = parseFloat(totalMoney) + 0.05;
        moneyField.val(totalMoney.toFixed(2));

    });
}
function addDime() {
    $('#addDime').click(function () {
        var moneyField = $('#totalMoney');
        var totalMoney = $('#totalMoney').val();
        //var totalMoneyTwoDecimals = totalMoney.toFixed(2);
        totalMoney = parseFloat(totalMoney) + 0.10;
        totalMoneyTwoDecimals = totalMoney.toFixed(2);
        moneyField.val(totalMoneyTwoDecimals);

    });
}


function makeChange() {
    resetChangeReturn();

    var change = $('#totalMoney').val();
    var floatMoney = parseFloat(change);
    var floatMoneyTwoDecimals = floatMoney.toFixed(2);
    var cents = floatMoneyTwoDecimals * 100;
    cents = cents.toFixed(0);
//    var stringCents = cents.toString();
    var dollars = parseInt(cents / 100);
    cents = cents % 100;
    var quarters = parseInt(cents / 25);
    cents = cents % 25;
    var dimes = parseInt(cents / 10);
    cents = cents % 10;
    var nickels = parseInt(cents / 5);
    cents = cents % 5;
    var pennies = parseInt(cents / 1);
    var stringDollars = dollars.toString();
    var stringQuarters = quarters.toString();
    var stringDimes = dimes.toString();
    var stringNickels = nickels.toString();
    var stringPennies = pennies.toString();
    var statement = [];

    if (dollars > 1) {
        var dollarStatement = stringDollars + " dollars";
        statement.push(dollarStatement);
    }
    if (dollars === 1) {
        dollarStatement = stringDollars + " dollar";
        statement.push(dollarStatement);
    }
    if (quarters > 1) {
        var quarterStatement = stringQuarters + " quarters";
        statement.push(quarterStatement);
    }
    if (quarters === 1) {
        quarterStatement = stringQuarters + " quarter";
        statement.push(quarterStatement);
    }
    if (dimes > 1) {
        var dimeStatement = stringDimes + " dimes";
        statement.push(dimeStatement);
    }
    if (dimes === 1) {
        dimeStatement = stringDimes + " dime";
        statement.push(dimeStatement);
    }
    if (nickels > 1) {
        var nickelStatement = stringNickels + " nickels";
        statement.push(nickelStatement);
    }
    if (nickels === 1) {
        nickelStatement = stringNickels + " nickel";
        statement.push(nickelStatement);
    }

    if (pennies > 1) {
        var pennyStatement = stringPennies + " pennies";
        statement.push(pennyStatement);
    }
    if (pennies === 1) {
        pennyStatement = stringPennies + " penny";
        statement.push(pennyStatement);
    }
//    if (dollarStatement !== null) {
//        statement = statement + dollarStatement;
//    }
//    if (quarterStatement !== null) {
//        statement = statement + quarterStatement;
//    }
//    if (dimeStatement !== null) {
//        statement = statement + dimeStatement;
//    }
//    if (nickelStatement !== null) {
//        statement = statement + nickelStatement;
//    }
//    if (pennyStatement !== null) {
//        statement = statement + pennyStatement;
//    }
    var changeStatement = [];
    $.each(statement, function (index, value) {
        if (value !== null) {
            changeStatement.push(value);
        }

    });
    var changeStatementWithComma = changeStatement.join(", ");

    $('#totalChange').val(changeStatementWithComma);
//    $('#totalChange').val("D: " + stringDollars + "Q: " + stringQuarters + "Di: " + stringDimes + "N: " + stringNickels + "P: " + stringPennies);
//    $('#totalChange').val(stringCents);
}

function changeReturn() {
    $('#changeReturn').click(function () {
        makeChange();
        //resetMoney();
    });
}
function resetChangeReturn() {
    $('#totalChange').empty;
}
function selectItem(itemNumber, itemId) {
    $('#selectedItemId').val(itemId);
//       var itemNumber = $('#itemNumber').val();
    $('#itemName').val(itemNumber);

}

function vendItem() {
//       var itemId = $('#selectedItemId').val();
//       var amount = $('#totalMoney').val();
//       var stringAmount = amount.toString();

    $('#purchase').click(function (event) {
        $.ajax({
            type: 'POST',
            url: 'http://tsg-vending.herokuapp.com/money/' + $('#totalMoney').val() + '/item/' + $('#selectedItemId').val(),
            success: function (data, status) {
//               $('#errorMessages').empty();
//                alert('SUCCESS!');
//**ADD HTML? FOR CHANGE?**--or make a function to iterate over object fields and add string to array??
//
//Response Body (no errors): JSON representation of Change object:
//{
//  “quarters”: 1,
//  “dimes”: 0,
//  “nickels”: 0,
//  “pennies”: 0
//}


//Response Body (no inventory): HTTP Status: 422 Unprocessable Entity
//{
//  "message": "SOLD OUT!!!"
//}


//Response Body (insufficient funds): HTTP Status: 422 Unprocessable Entity
//{
//  "message": "Please deposit: <amount short>"
//}
//
//Response Body (invalid item): HTTP Status: 422 Unprocessable Entity
//{ 
//  "message": "Invalid item selected" 
//} 

                $('#changeQuarters').val(data.quarters);
                $('#changeDimes').val(data.dimes);
                $('#changeNickels').val(data.nickels);
                $('#changePennies').val(data.pennies);
                $('#messages').val("Thank you!!");

            },
            error: function ($xhr) {
                var data = $xhr.responseJSON;
                $('#messages').val(data.message);
//               $('#errorMessages')
//                .append($('<li>')
//                .attr({class: 'list-group-item list-group-item-danger'})
//                .text('Error calling web service. Please try again later.')); 
            }
           
        });
        clearItems();
        loadItems();
    });
}
function clearItems(){
    $('#itemsRow').empty();
}
    