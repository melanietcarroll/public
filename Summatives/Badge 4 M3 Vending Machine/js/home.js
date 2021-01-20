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
            });
        },
        error: function ($xhr) {
            var data = $xhr.responseJSON;
            $('#vendingMessages').val(data.message);
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
        totalMoney = parseFloat(totalMoney) + 0.10;
        moneyField.val(totalMoney.toFixed(2));
    });
}
function makeChange() {
    resetChangeReturn();

    var change = $('#totalMoney').val();
    var floatMoney = parseFloat(change);
    var floatMoneyTwoDecimals = floatMoney.toFixed(2);
    var cents = floatMoneyTwoDecimals * 100;
    cents = cents.toFixed(0);

    var quarters = parseInt(cents / 25);
    cents = cents % 25;
    var dimes = parseInt(cents / 10);
    cents = cents % 10;
    var nickels = parseInt(cents / 5);
    cents = cents % 5;
    var pennies = parseInt(cents / 1);

    var stringQuarters = quarters.toString();
    var stringDimes = dimes.toString();
    var stringNickels = nickels.toString();
    var stringPennies = pennies.toString();

    var statement = [];

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
    var changeStatementWithComma = statement.join(", ");
    $('#totalChange').val(changeStatementWithComma);
}
function changeReturn() {
    $('#changeReturn').click(function () {
        resetChangeReturn();
        makeChange();
        resetMoney();
        clearItems();
        loadItems();
        clearMessages();
        deselectItem();
    });
}
function clearMessages() {
    $('#vendingMessages').val('');
}
function resetChangeReturn() {
    $('#totalChange').val('');
}
function selectItem(itemNumber, itemId) {
    $('#selectedItemId').val(itemId);
    $('#itemName').val(itemNumber);
}
function deselectItem() {
    $('#selectedItemId').val('');
    $('#itemName').val('');
}
function vendItem() {
    $('#purchase').click(function (event) {
        $.ajax({
            type: 'POST',
            url: 'http://tsg-vending.herokuapp.com/money/' + $('#totalMoney').val() + '/item/' + $('#selectedItemId').val(),
            success: function (data, status) {

                $('#vendingMessages').val("Thank you!!");
                var statement = [];

                if (data.quarters > 1) {
                    var quarterStatement = data.quarters.toString() + " quarters";
                    statement.push(quarterStatement);
                }
                if (data.quarters === 1) {
                    quarterStatement = data.quarters.toString() + " quarter";
                    statement.push(quarterStatement);
                }
                if (data.dimes > 1) {
                    var dimeStatement = data.dimes.toString() + " dimes";
                    statement.push(dimeStatement);
                }
                if (data.dimes === 1) {
                    dimeStatement = data.dimes.toString() + " dime";
                    statement.push(dimeStatement);
                }
                if (data.nickels > 1) {
                    var nickelStatement = data.nickels.toString() + " nickels";
                    statement.push(nickelStatement);
                }
                if (data.nickels === 1) {
                    nickelStatement = data.nickels.toString() + " nickel";
                    statement.push(nickelStatement);
                }

                if (data.pennies > 1) {
                    var pennyStatement = data.pennies.toString() + " pennies";
                    statement.push(pennyStatement);
                }
                if (data.pennies === 1) {
                    pennyStatement = data.pennies.toString() + " penny";
                    statement.push(pennyStatement);
                }
                var changeStatementWithComma = statement.join(", ");
                $('#totalChange').val(changeStatementWithComma);
            },
            error: function (errorResponse) {
                var data = errorResponse.responseJSON;
                $('#vendingMessages').val(data.message);
            }
        });
        clearItems();
        loadItems();
    });
}
function clearItems() {
    $('#itemsRow').empty();
}
