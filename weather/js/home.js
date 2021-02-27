$(document).ready(function () {
    getWeatherByZip();
    getExtendedForecast();
    clearCurrentForecast();
});
function getWeatherByZip() {
    $('#searchButton').click(function (event) {
        var title = $('#title');
        var image = $('#currentWeatherImage');
        var currentTemp = $('#currentZipTemperature');
        var currentHum = $('#currentHumidity');
        var currentWind = $('#currentWind');
        var APIKEY = "YourAPIKeyhere";
        var zip = $('#addZipCode').val();
        var units = $('#unitSelect').val();
        $.ajax({
            type: 'GET',
            url: 'http://api.openweathermap.org/data/2.5/weather?zip=' + zip + ',us&units=' + units + '&appid=' + APIKEY,
            success: function (data, status) {
                var currentWeather = data.weather[0].main;
                var weatherDescription = data.weather[0].description;
                var temp = data.main.temp;
                var humidity = data.main.humidity;
                var wind = data.wind.speed;

                var name = data.name;
                var cityTitle = '<h2>' + 'Current conditions in ' + name + '</h2>';
                title.append(cityTitle);

                var currentWeatherPic = data.weather[0].icon;
                var currentPicDiv = '<img src="http://openweathermap.org/img/w/' + currentWeatherPic + '.png" class="img-fluid">';
                currentPicDiv += '<span>' + currentWeather + ': ' + weatherDescription + '</span>';
                image.append(currentPicDiv);

                if (units == "imperial") {
                    var currentTempDiv = '<p>' + 'Temperature: ' + temp + '&degF' + '</p>';
                    var windCondDiv = '<p>Wind: ' + wind + ' miles/hour' + '</p>';
                }
                if (units == "metric") {
                    var currentTempDiv = '<p>' + 'Temperature: ' + temp + '&degC' + '</p>';
                    var windCondDiv = '<p>Wind: ' + wind + ' km/hour' + '</p>'
                }
                currentTemp.append(currentTempDiv);

                var humidityDiv = '<p>Humidity: ' + humidity + '%' + '</p>';
                currentHum.append(humidityDiv);
                currentWind.append(windCondDiv);
            },

            error: function () {
                $('#errorMessages')
                        .append($('<li>')
                                .attr({class: 'list-group-item list-group-item-danger'})
                                .text('Error calling web service. Please try again later.'));
            }

        });
    });
}
function clearCurrentForecast() {
    $('#searchButton').click(function (event) {
        $('#fiveDay').empty();
        $('#addZipCode').val('');
        $('#unitSelect').val('imperial');
        $('#title').empty();
        $('#currentWeatherImage').empty();
        $('#currentZipTemperature').empty();
        $('#currentHumidity').empty();
        $('#currentWind').empty();
    });
}
function getExtendedForecast() {
    $('#searchButton').click(function (event) {
        var forecast = $('#fiveDay');
        var APIKEY = "YourAPIKeyhere";
        var zip = $('#addZipCode').val();
        var units = $('#unitSelect').val();

        $.ajax({
            type: 'GET',
            url: 'http://api.openweathermap.org/data/2.5/forecast?zip=' + zip + ',us&units=' + units + '&appid=' + APIKEY,
            success: function (data) {
                console.log(data);
                var wf = "";
                var low = "";
                var div = '<div class="col-md-2"></div>';
                var months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];

                $.each(data.list, function (index, val) {
                    var timeMax = "15:00:00";
                    var timeMin = "03:00:00";
                    var fullDate = val.dt_txt;
                    var month = fullDate.substring(5, 7);
                    var monthName = months[month - 1];
                    var day = fullDate.substring(8, 10);
                    

                    if (val.dt_txt.includes(timeMax) && units === "imperial") {
                        wf += '<div class="col-md-2">';
                        wf += "<p>" + day + " " + monthName + "</p>"; // Date
                        wf += "<span>" + val.weather[0].description + "</span>"; // Description
                        wf += "<img src='https://openweathermap.org/img/w/" + val.weather[0].icon + ".png'>";// Icon
                        wf += "<p>"  + "Day "+ val.main.temp + "&degF" + "</p>"// Temperature
                        wf += "</div>";
                    }
                    if (val.dt_txt.includes(timeMax) && units === "metric"){
                        wf += '<div class="col-md-2">';
                        wf += "<p>" + day + " " + monthName + "</p>"; // Date
                        wf += "<span>" + val.weather[0].description + "</span>"; // Description
                        wf += "<img src='https://openweathermap.org/img/w/" + val.weather[0].icon + ".png'>";// Icon
                        wf += "<p>"  + "Day " +val.main.temp + "&degC" + "</p>"// Temperature
                        wf += "</div>";
                    }
                    if (val.dt_txt.includes(timeMin)&& units === "imperial") {
                        low += '<div class="col-md-2">';
                        low += '<span>' + "Night " + val.main.temp + "&degF" + "</span>";
                        low += "</div>";
                    }
                    if (val.dt_txt.includes(timeMin) && units === "metric"){
                        low += '<div class="col-md-2">';
                        low += '<span>' + "Night "+ val.main.temp + "&degC" + "</span>";
                        low += "</div>";
                    }

                });

                forecast.prepend(div);
                forecast.prepend(wf);
                forecast.append(low);
            },

            error: function () {
                $('#errorMessages')
                        .append($('<li>')
                                .attr({class: 'list-group-item list-group-item-danger'})
                                .text('Error calling web service. Please try again later.'));
            }
        });
    });
}

