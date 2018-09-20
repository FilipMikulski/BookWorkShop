
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="./style.css">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-6">
            <div class="modal-header">
                <h3 class="modal-title">Lista książek:</h3>
            </div>
            <div class="modal-body">
                <ul class="books-list col-md-12">
                </ul>
            </div>
        </div>
        <div class="col-lg-6">
            <form class="book-form-add">
                <div class="modal-header">
                    <h3 class="modal-title">Dodaj nową książkę:</h3>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Tytuł Książki:</label>
                        <input type='text' class="book-form-title form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Autor Książki:</label>
                        <select class="form-control authors-list-select">
                            <option value="">Wybierz z listy:</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Wydawca:</label>
                        <input type='text' class="book-form-publisher form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Gatunek:</label>
                        <input type='text' class="book-form-type form-control" required>
                    </div>
                    <div class="form-group">
                        <label>ISBN:</label>
                        <input type='text' class="book-form-isbn form-control" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="submit" class="btn btn-primary book-form-submit btn-sm" value="Dodaj książkę">
                </div>
            </form>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-6">
            <div class="modal-header">
                <h3 class="modal-title">Lista autorów:</h3>
            </div>
            <div class="modal-body">
                <ul class="authors-list col-md-12">
                </ul>
            </div>
        </div>
        <div class="col-lg-6">
            <form class="author-form-add">
                <div class="modal-header">
                    <h3 class="modal-title">Dodaj nowego autora:</h3>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Imię Autora:</label>
                        <input type='text' class="author-form-name form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Nazwisko Autora:</label>
                        <input type='text' class="author-form-surname form-control" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="submit" class="btn btn-primary author-form-submit btn-sm" value="Dodaj autora">
                </div>
            </form>
        </div>
    </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<script src="./app.js"></script>
</body>
</html>
