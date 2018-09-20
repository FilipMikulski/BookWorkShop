$(function () {

    var ul = $(".books-list");
    var ulAuthors = $(".authors-list");
    var authorsSelectList = $(".authors-list-select");

    var booksUrl = 'http://localhost:8080/books/';
    var authorsUrl = 'http://localhost:8080/authors/';

    function request(baseUrl, onDone, id, method, data, dataType) {
        var BASE_URL = baseUrl
        var url = id ?
            BASE_URL + id :
            BASE_URL
        $.ajax({
            url: url,
            method: method || 'GET',
            data: JSON.stringify(data) || null,
            dataType: dataType || null,
            contentType: 'application/json',
            success: onDone
        })
    }

    function getBooks() {
        function onDone(books) {
            ul.html("");
            books.forEach(function (book) {
                var li = $('<li>',{"class":"row","data-id":book.id});
                var divTitle = $('<div>',{"class":"col-7 book-title"});
                var divDetails = $('<div>',{"class":"book-details"}).hide();
                var divFormToEdit = $('<div>',{"class":"book-form-edit-wrapper col-12"}).hide();
                var divButtons = $("<div>",{"class":"book-buttons col-5"})
                var deleteButton = $('<input>',{"type":"button","value":"Usuń","class":"book-button-delete btn-space btn btn-danger btn-sm"});
                var editButton = $('<input>',{"type":"button","value":"Edytuj","class":"book-button-edit btn-space btn btn-warning btn-sm"});
                divButtons.append(editButton).append(deleteButton);

                var bookTitle=$("<h5>");
                bookTitle.append(book.title);
                divTitle.append(bookTitle);
                li.append(divTitle).append(divButtons).append(divDetails).append(divFormToEdit);
                ul.append(li)

            })

        }

        request(booksUrl, onDone);
    }

    function getBook(bookId, onDone) {
        request(booksUrl, onDone, bookId)
    }

    function addBook(onDone, data) {
        request(booksUrl, onDone, null, 'POST', data, "json")
    }

    function editBook(bookId, onDone, data) {
        request(booksUrl, onDone, bookId, "PUT", data, "text")
    }


    function removeBook(bookId, onDone) {
        request(booksUrl, onDone, bookId, "DELETE", null, "text")
    }

    getBooks();


    // Metody Ajax dla Authors

    function getAuthors() {
        function onDone(authors) {
            ulAuthors.html("");
            var defaultOption = $("<option>").attr("value","");
            defaultOption.append("Wybierz z listy");
            authorsSelectList.html(defaultOption);
            authors.forEach(function (author) {
                var li = $('<li>',{"class":"row","data-id":author.id});
                var divAuthorName = $('<div>',{"class":"col-7 author-title"});
                var divFormToEdit = $('<div>',{"class":"author-form-edit-wrapper col-12"}).hide();
                var divButtons = $("<div>",{"class":"author-buttons col-5"})
                var deleteButton = $('<input>',{"type":"button","value":"Usuń","class":"author-button-delete btn-space btn btn-danger btn-sm"});
                var editButton = $('<input>',{"type":"button","value":"Edytuj","class":"author-button-edit btn-space btn btn-warning btn-sm"});
                divButtons.append(editButton).append(deleteButton);

                var authorName=$("<h5>");
                authorName.append(author.firstName + " " + author.lastName);
                divAuthorName.append(authorName);
                li.append(divAuthorName).append(divButtons).append(divFormToEdit);
                ulAuthors.append(li)


                var option = $("<option>").attr("value",author.id);
                var authorInfo = author.firstName + " " + author.lastName;
                option.append(authorInfo);
                authorsSelectList.append(option);


            })


        }

        request(authorsUrl, onDone)
    }

    getAuthors();


    function getAuthor(authorId, onDone) {
        request(authorsUrl, onDone, authorId)
    }

    function addAuthor(onDone, data) {
        request(authorsUrl, onDone, null, 'POST', data, "json")
    }

    function editAuthor(authorId, onDone, data) {
        request(authorsUrl, onDone, authorId, "PUT", data, "text")
    }


    function removeAuthor(authorId, onDone) {
        request(authorsUrl, onDone, authorId, "DELETE", null, "text")
    }



    // dodawanie nowej książki

    var formToAddBooks = $('.book-form-add');

    formToAddBooks.on('submit', function (ev) {
        ev.preventDefault();

        function onDone() {
            // location.reload()
            getBooks();
            console.log("Wczytuję nową listę!")
        }

        var data = {
            'isbn': $(this).closest("form").find('.book-form-isbn').val(),
            'title': $(this).closest("form").find('.book-form-title').val(),
            'publisher': $(this).closest("form").find('.book-form-publisher').val(),
            'type': $(this).closest("form").find('.book-form-type').val(),
            'author': $(this).closest("form").find('.authors-list-select').val()
        };

        addBook(onDone, data)
    });

    // Edycja książki


    ul.on("submit",".book-form-edit", function (event) {

        event.preventDefault();
        var self = $(this);
        var bookId = self.closest("li").data("id");

        console.log(bookId)

        function onDone() {
            getBooks();

        }

        console.log($(this).closest("form").find('.book-form-author').val());
        console.log($(this).closest("form").find('.book-form-isbn').val())

        var data = {
            'isbn': $(this).closest("form").find('.book-form-isbn').val(),
            'title': $(this).closest("form").find('.book-form-title').val(),
            'publisher': $(this).closest("form").find('.book-form-publisher').val(),
            'type': $(this).closest("form").find('.book-form-type').val(),
            'author': $(this).closest("form").find('.authors-list-select').val()
        };

        editBook(bookId,onDone,data)



    })


    // Usuwanie książki

    ul.on('click', '.book-buttons .book-button-delete', function () {
        var self = $(this)
        var bookId = self.closest("li").data('id');

        function onDone() {
            self.closest("li").remove()
        }

        removeBook(bookId, onDone)
    })

    // Wyświetlanie formularza do edycji książki

    ul.on("click",'.book-buttons .book-button-edit',function () {
        var self = $(this);
        var bookId = $(this).closest("li").data('id');
        var div = self.closest("li").find(".book-form-edit-wrapper");

        if(div.data("loaded") != true) {

            function onDone(book){

                div.attr("data-loaded","true")

                var form = $("<form>",{"class":"book-form-edit"});
                var inputBookTitle = $("<input>",{"type":"text","class":"book-form-title form-control","value":book.title, "required":"required"});

                // var inputBookAuthor = $("<input>",{"type":"text","class":"book-form-author form-control", "value":book.author.firstName + " " + book.author.lastName, "required":"required"});
                var selectBookAuthor = $("<select>",{"class":"form-control authors-list-select", "required":"required","data-author":book.author.id})
                var inputBookPublisher = $("<input>",{"type":"text","class":"book-form-publisher form-control", "value":book.publisher, "required":"required"});
                var inputBookType = $("<input>",{"type":"text","class":"book-form-type form-control", "value":book.type, "required":"required"});
                var inputBookIsbn = $("<input>",{"type":"text","class":"book-form-isbn form-control", "value":book.isbn, "required":"required"});
                var inputBookEditButton = $("<input>",{"type":"submit","class":"book-form-submit btn btn-dark btn-sm","value":"Zapisz zmiany"});

                var inputs = [inputBookTitle,selectBookAuthor,inputBookPublisher,inputBookType,inputBookIsbn];
                var inputsLabel = ["Tytuł: ", "Autor: ", "Wydawca: ", "Gatunek: ", "ISBN"];

                var divModalHeader = $("<div>",{"class":"modal-header"});
                var header = $("<p>",{"class":"modal-title"}).append("Edytuj książkę:");

                divModalHeader.append(header);

                var divModalBody = $("<div>", {"class":"modal-body"});

                inputs.forEach(function (input,index) {
                    var divFormGroup = $("<div>",{"class":"form-group"})
                    var label = $("<label>");
                    label.append(inputsLabel[index]);
                    divFormGroup.append(label).append(input)
                    divModalBody.append(divFormGroup);
                });

                var divModalFooter = $("<div>",{"class":"modal-footer"});
                divModalFooter.append(inputBookEditButton);

                form.append(divModalHeader).append(divModalBody).append(divModalFooter);


                div.append(form)

                authorsSelectList = $(".authors-list-select");

                getAuthors();

                div.slideDown();
            }

            getBook(bookId, onDone)

        } else {
            div.slideToggle();
        }
    });


    // wyświetlanie informacji o książce

    ul.on("click",'.book-title',function () {
        var self = $(this);
        var bookId = $(this).closest("li").data('id');
        var div = self.closest("li").find(".book-details");

        if(div.data("loaded") != true) {

            function onDone(book){

                div.attr("data-loaded","true");

                var info = [
                    book.author.firstName +" "+book.author.lastName,
                    book.type,
                    book.publisher,
                    book.isbn
                ]

                var labels = [
                    "Autor: ",
                    "Gatunek: ",
                    "Wydawca: ",
                    "ISBN: "
                ]

                var ul = $("<ul>");

                info.forEach(function (detail, index) {
                    var li = $("<li>");
                    li.append(labels[index]).append(detail)
                    ul.append(li);
                })

                div.append(ul);

                div.slideDown();
            }

            getBook(bookId, onDone)

        } else {
            div.slideToggle();
        }
    });


    // Ajax dla authora

    // dodawanie nowego autora

    var formToAddAuthor = $('.author-form-add');

    formToAddAuthor.on('submit', function (ev) {
        ev.preventDefault();

        function onDone() {
            // location.reload()
            getAuthors();
            console.log("Wczytuję nową listę authorów!")
        }

        var data = {
            'firstName': $(this).closest("form").find('.author-form-name').val(),
            'lastName': $(this).closest("form").find('.author-form-surname').val()
        };

        addAuthor(onDone, data)
    });

    // Wyświetlanie formularza do edycji autora

    ulAuthors.on("click",'.author-buttons .author-button-edit',function () {
        var self = $(this);
        var authorId = $(this).closest("li").data('id');
        var div = self.closest("li").find(".author-form-edit-wrapper");

        if(div.data("loaded") != true) {

            function onDone(author){

                div.attr("data-loaded","true")

                var form = $("<form>",{"class":"author-form-edit"});
                var inputAuthorFirstName = $("<input>",{"type":"text","class":"author-form-name form-control","value":author.firstName, "required":"required"});
                var inputAuthorLastName = $("<input>",{"type":"text","class":"author-form-surname form-control", "value":author.lastName, "required":"required"});
                var inputAuthorEditButton = $("<input>",{"type":"submit","class":"author-form-submit btn btn-dark btn-sm","value":"Zapisz zmiany"});

                var inputs = [inputAuthorFirstName,inputAuthorLastName];
                var inputsLabel = ["Imię: ", "Nazwisko: "];

                var divModalHeader = $("<div>",{"class":"modal-header"});
                var header = $("<p>",{"class":"modal-title"}).append("Edytuj autora:");

                divModalHeader.append(header);

                var divModalBody = $("<div>", {"class":"modal-body"});

                inputs.forEach(function (input,index) {
                    var divFormGroup = $("<div>",{"class":"form-group"})
                    var label = $("<label>");
                    label.append(inputsLabel[index]);
                    divFormGroup.append(label).append(input)
                    divModalBody.append(divFormGroup);
                });

                var divModalFooter = $("<div>",{"class":"modal-footer"});
                divModalFooter.append(inputAuthorEditButton);

                form.append(divModalHeader).append(divModalBody).append(divModalFooter);


                div.append(form);

                div.slideDown();
            }

            getAuthor(authorId, onDone)

        } else {
            div.slideToggle();
        }
    });

    // Edycja autora


    ulAuthors.on("submit",".author-form-edit", function (event) {

        event.preventDefault();
        var self = $(this);
        var authorId = self.closest("li").data("id");

        console.log(authorId)

        function onDone() {
            getAuthors();

        }

        var data = {
            'firstName': $(this).closest("form").find('.author-form-name').val(),
            'lastName': $(this).closest("form").find('.author-form-surname').val()
        };

        editAuthor(authorId,onDone,data)


    })

    // Usuwanie autora

    ulAuthors.on('click', '.author-buttons .author-button-delete', function () {
        var self = $(this)
        var authorId = self.closest("li").data('id');

        function onDone() {
            self.closest("li").remove()
        }

        removeAuthor(authorId, onDone)
    })


});

