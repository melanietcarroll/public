$(document).ready(function () {
    loadDvds();
    addDvd();
    updateDvd();
    deleteDvd();
    findADvd()
});

function findADvd() {
$('#search').click(function(event) {
    clearDvdTable();
    var contentRows = $('#contentRows');
    $.ajax({
        type: 'GET',
        url: 'https://tsg-dvds.herokuapp.com/dvds/'+ $('#searchCategory').val() + '/' + $('#searchTerm').val(),
        success: function (dvdArray) {
            $.each(dvdArray, function (index, dvd) {
                var title = dvd.title;
                var releaseYear = dvd.releaseYear;
                var director = dvd.director;
                var rating = dvd.rating;
                var notes = dvd.notes;
                var dvdId = dvd.id;

                var row = '<tr>';
                row += '<td onclick="showDvdDetails(' + dvdId + ')" id="title">' + '<u>' + title + '<u>' + '</td>';
                row += '<td>' + releaseYear + '</td>';
                row += '<td>' + director + '</td>';
                row += '<td>' + rating + '</td>';
                row += '<td><button type="button" class="btn btn-info" id="editButton" onclick="showEditDvdForm(' + dvdId + ')">Edit</button></td>';
                row += '<td><button type="button" class="btn btn-danger" onclick="deleteDvd(' + dvdId + ')">Delete</button></td>';
                row += '</tr>';

                contentRows.append(row);
            });

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




function loadDvds() {
    clearDvdTable()
    var contentRows = $('#contentRows');

    $.ajax({
        type: 'GET',
        url: 'https://tsg-dvds.herokuapp.com/dvds',
        success: function (dvdArray) {
            $.each(dvdArray, function (index, dvd) {
                var title = dvd.title;
                var releaseYear = dvd.releaseYear;
                var director = dvd.director;
                var rating = dvd.rating;
                var notes = dvd.notes;
                var dvdId = dvd.id;

                var row = '<tr>';
                row += '<td onclick="showDvdDetails(' + dvdId + ')" id="title">' + '<u>' + title + '<u>' + '</td>';
                row += '<td>' + releaseYear + '</td>';
                row += '<td>' + director + '</td>';
                row += '<td>' + rating + '</td>';
                row += '<td><button type="button" class="btn btn-info" id="editButton" onclick="showEditDvdForm(' + dvdId + ')">Edit</button></td>';
                row += '<td><button type="button" class="btn btn-danger" id="deleteButton" onclick="deleteDvd(' + dvdId + ')">Delete</button></td>';
                row += '</tr>';

                contentRows.append(row);
            });

        },
        error: function () {
            $('#errorMessages')
                    .append($('<li>')
                            .attr({class: 'list-group-item list-group-item-danger'})
                            .text('Error calling web service. Please try again later.'));
        }
    });
}
function clearDvdTable() {
    $('#contentRows').empty();
}

function addDvd() {
     var haveValidationErrors = checkAndDisplayValidationErrors($('#addForm').find('input'));
        
        if(haveValidationErrors) {
            return false;
        }
    $('#createDVDButton').click(function(event) {
        $.ajax({
            type: 'POST',
            url: 'https://tsg-dvds.herokuapp.com/dvd',
            data: JSON.stringify({
                title: $('#createDvdTitle').val(),
                releaseYear: $('#createReleaseYear').val(),
                director: $('#createDirector').val(),
                rating: $('#createRating').val(),
                notes: $('#createNotes').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function () {
                $('#errorMessages').empty();
                $('#createDvdTitle').val('');
                $('#createReleaseYear').val('');
                $('#createDirector').val('');
                $('#createRating').val('G');
                $('#createNotes').val('This really is a great tale!');

                hideCreateDvdForm();
                loadDvds();
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

function showCreateDvdForm() {
    $('#errorMessages').empty();

    $('#dvdTableInfo').hide();
    $('#createDVD').show();
}

function hideCreateDvdForm() {
    $('#errorMessages').empty();

    $('#createDvdTitle').val('');
    $('#createReleaseYear').val('');
    $('#createDirector').val('');
    $('#createRating').val('');
    $('#createNotes').val('');

    $('#dvdTableInfo').show();
    $('#createDVD').hide();
}
function showEditDvdForm(dvdId) {
    $('#errorMessages').empty();

    $.ajax({
        type: 'GET',
        url: 'https://tsg-dvds.herokuapp.com/dvd/' + dvdId,
        success: function (data, status) {
            $('#editDvdId').val(data.id);
            $('#editDvdTitle').val(data.title);
            $('#editReleaseYear').val(data.releaseYear);
            $('#editDirector').val(data.director);
            $('#editRating').val(data.rating);
            $('#editNotes').val(data.notes);

            var title = '<h1>' + 'Edit Dvd: ' + data.title + '</h1>'
            var dvdTitle = $('#editDvdTitleInput');
            dvdTitle.append(title);

        },
        error: function () {
            $('#errorMessages')
                    .append($('<li>')
                            .attr({class: 'list-group-item list-group-item-danger'})
                            .text('Error calling web service. Please try again later.'));
        }
    })

    
    $('#dvdTableInfo').hide();
    $('#editDVD').show();
}

function hideEditDvdForm() {
    $('#errorMessages').empty();

    $('#editDvdTitle').val('');
    $('#editReleaseYear').val('');
    $('#editDirector').val('');
    $('#editRating').val('G');
    $('#editNotes').val('This really is a great tale!');
    $('#editDvdTitleInput').empty();

    
    $('#dvdTableInfo').show();
    $('#editDVD').hide();
}
function updateDvd(dvdId) {
     var haveValidationErrors = checkAndDisplayValidationErrors($('#addForm').find('input'));
        
        if(haveValidationErrors) {
            return false;
        }
    $('#editButton').click(function(event) {
        $.ajax({
            type: 'PUT',
            url: 'https://tsg-dvds.herokuapp.com/dvd/' + $('#editDvdId').val(),
            data: JSON.stringify({
                dvdId: $('#editDvdId').val(),
                title: $('#editDvdTitle').val(),
                releaseYear: $('#editReleaseYear').val(),
                director: $('#editDirector').val(),
                rating: $('#editRating').val(),
                notes: $('#editNotes').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function () {
                $('#errorMessages').empty();
                hideEditDvdForm();
                loadDvds();
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
function deleteDvd(dvdId) {

    $.ajax({
        type: 'DELETE',
        url: 'https://tsg-dvds.herokuapp.com/dvd/' + dvdId,
        success: function () {
            loadDvds();
        }
    });

}

function showDvdDetails(dvdId) {
    $('#errorMessages').empty();

    $.ajax({
        type: 'GET',
        url: 'https://tsg-dvds.herokuapp.com/dvd/' + dvdId,
        success: function (data, status) {
            $('#editDvdId').val(data.id);
            $('#editDvdTitle').val(data.title);
            $('#editReleaseYear').val(data.releaseYear);
            $('#editDirector').val(data.director);
            $('#editRating').val(data.rating);
            $('#editNotes').val(data.notes);

            var title = '<h1>' + data.title + '</h1>'
            var dvdTitle = $('#dvdDetailsTitle');
            dvdTitle.append(title);

            var entry = '<p>' + data.releaseYear + '</p>';
            var releaseYear = $('#releaseYearDetails');
            releaseYear.append(entry);

            var directorEntry = '<p>' + data.director + '</p>'
            var director = $('#directorDetails');
            director.append(directorEntry);

            var ratingEntry = '<p>' + data.rating + '</p>'
            var ratingDetails = $('#ratingDetails');
            ratingDetails.append(ratingEntry);

            var notesEntry = '<p>' + data.notes + '</p>'
            var notesDetails = $('#notesDetails');
            notesDetails.append(notesEntry);



        },
        error: function () {
            $('#errorMessages')
                    .append($('<li>')
                            .attr({class: 'list-group-item list-group-item-danger'})
                            .text('Error calling web service. Please try again later.'));
        }
    })


    $('#dvdTableInfo').hide();
    $('#dvdDetails').show();
}
function hideDvdDetailsPage() {
    $('#errorMessages').empty();

    $('#dvdDetailsTitle').empty();
    $('#releaseYearDetails').empty();
    $('#directorDetails').empty();
    $('#ratingDetails').empty();
    $('#notesDetails').empty();

    $('#dvdTableInfo').show();
    $('#dvdDetails').hide();
}
function checkAndDisplayValidationErrors(input) {
    $('#errorMessages').empty();
    
    var errorMessages = [];
    
    input.each(function() {
        if (!this.validity.valid) {
            var errorField = $('label[for=' + this.id + ']').text();
            errorMessages.push(errorField + ' ' + this.validationMessage);
        }  
    });
    
    if (errorMessages.length > 0){
        $.each(errorMessages,function(index,message) {
            $('#errorMessages').append($('<li>').attr({class: 'list-group-item list-group-item-danger'}).text(message));
        });
        // return true, indicating that there were errors
        return true;
    } else {
        // return false, indicating that there were no errors
        return false;
    }
}
// var changeStatement = [];
//                $.each(statement, function (index, value) {
//                    if (value !== null) {
//                        changeStatement.push(value);
//                    }
//                });