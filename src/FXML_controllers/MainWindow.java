package FXML_controllers;

import book_store.*;
import hibernateControllers.BookHibController;
import hibernateControllers.CartHibController;
import hibernateControllers.UserHibController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {
    @FXML
    public ListView bookList;
    @FXML
    public TextField bookTitle;
    public TextArea bookDescription;
    public ComboBox bookGenre;
    public DatePicker publDate;
    public TextField pgNum;
    public TextField bookAuthor;
    public TextField bookPrice;
    public TextField bookQuantity;
    public ListView bookListMngr;
    public Tab BookShopListTab;
    public Tab ManageBooksTab;
    public ComboBox bookLang;
    public Tab ShoppingCartTab;
    public Tab ManageUsersTab;
    public MenuItem comment;
    public MenuItem viewComments;
    public ComboBox genreCmb;
    public TextField priceFromF;
    public TextField priceToF;
    public ComboBox languageCmb;

    public TableView usersTable;

    public TableColumn<UsersTableParams, String> idCol;
    public TableColumn<UsersTableParams, String> userTypeCol;
    public TableColumn<UsersTableParams, String> loginCol;
    public TableColumn<UsersTableParams, String> createDateCol;
    public TableColumn<UsersTableParams, String> emailCol;
    public TableColumn<UsersTableParams, String> posCol;
    public TableColumn<UsersTableParams, String> phoneCol;
    public TableColumn<UsersTableParams, String> nameCol;
    public TableColumn<UsersTableParams, String> surnameCol;
    public TableColumn<UsersTableParams, String> companyNameCol;
    public TableColumn<UsersTableParams, Void> actionColBtn;
    public ListView orderDetailsList;
    public ListView MyOrdersList;

    private ObservableList<UsersTableParams> data = FXCollections.observableArrayList();

    private int userId;

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GlobeBookShop");
    BookHibController bookHibController = new BookHibController(entityManagerFactory);
    UserHibController userHibController = new UserHibController(entityManagerFactory);
    CartHibController cartHibController = new CartHibController(entityManagerFactory);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        genreCmb.getItems().add("All");
        genreCmb.getItems().addAll(eBookGenre.values());
        genreCmb.setValue("All");
        languageCmb.getItems().add("All");
        languageCmb.getItems().addAll(eBookLang.values());
        languageCmb.setValue("All");

        bookGenre.getItems().addAll(eBookGenre.values());
        bookLang.getItems().addAll(eBookLang.values());
        refreshBookLists(bookHibController.getAllBooks(false));

        //User table actions
        {
            usersTable.getItems().clear();

            idCol.setCellValueFactory(new PropertyValueFactory<UsersTableParams, String>("id"));
            idCol.setCellFactory(TextFieldTableCell.forTableColumn());
            idCol.setOnEditCommit(
                    t -> t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setId(t.getNewValue())
            );
            userTypeCol.setCellValueFactory(new PropertyValueFactory<UsersTableParams, String>("userType"));
            userTypeCol.setCellFactory(TextFieldTableCell.forTableColumn());
            userTypeCol.setOnEditCommit(
                    t -> t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setUserType(t.getNewValue())
            );
            loginCol.setCellValueFactory(new PropertyValueFactory<UsersTableParams, String>("login"));
            loginCol.setCellFactory(TextFieldTableCell.forTableColumn());
            loginCol.setOnEditCommit(
                    t -> {
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setLogin(t.getNewValue());
                        User user = userHibController.getUserById(Integer.parseInt(t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).getId()));
                        user.setLogin(t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).getLogin());
                        userHibController.updateUser(user);
                    }
            );
            createDateCol.setCellValueFactory(new PropertyValueFactory<UsersTableParams, String>("createDate"));
            createDateCol.setCellFactory(TextFieldTableCell.forTableColumn());
            createDateCol.setOnEditCommit(
                    t -> t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setCreateDate(t.getNewValue())
            );
            posCol.setCellValueFactory(new PropertyValueFactory<UsersTableParams, String>("pos"));
            posCol.setCellFactory(TextFieldTableCell.forTableColumn());
            posCol.setOnEditCommit(
                    t -> {
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setPos(t.getNewValue());
                        User user = userHibController.getUserById(Integer.parseInt(t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).getId()));
                        if (user.getClass() == Employee.class) {
                            Employee employee = (Employee) user;
                            employee.setPos(eEmpPositions.valueOf(t.getTableView().getItems().get(
                                    t.getTablePosition().getRow()).getPos()));
                            userHibController.updateUser(employee);
                        }
                        else {
                            AlertMessage.generateMessage("Not editable", "To edit the user should be of class Employee");
                            t.getTableView().getItems().get(
                                    t.getTablePosition().getRow()).setPos("");
                        }

                    }

            );
            emailCol.setCellValueFactory(new PropertyValueFactory<UsersTableParams, String>("email"));
            emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
            emailCol.setOnEditCommit(
                    t -> {
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setEmail(t.getNewValue());
                        User user = userHibController.getUserById(Integer.parseInt(t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).getId()));
                        if (user.getClass() == Customer.class){
                            Customer customer = (Customer) user;
                            customer.setEmail(t.getTableView().getItems().get(
                                    t.getTablePosition().getRow()).getEmail());
                            userHibController.updateUser(customer);
                        }
                        else {
                            AlertMessage.generateMessage("Not editable", "To edit the user should be of class Customer");
                            t.getTableView().getItems().get(
                                    t.getTablePosition().getRow()).setEmail("");
                        }

                    }
            );
            phoneCol.setCellValueFactory(new PropertyValueFactory<UsersTableParams, String>("phone"));
            phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
            phoneCol.setOnEditCommit(
                    t -> {
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setPhone(t.getNewValue());
                        User user = userHibController.getUserById(Integer.parseInt(t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).getId()));
                        if (user.getClass() == Customer.class){
                            Customer customer = (Customer) user;
                            customer.setPhone(t.getTableView().getItems().get(
                                    t.getTablePosition().getRow()).getPhone());
                            userHibController.updateUser(customer);
                        }
                        else {
                            AlertMessage.generateMessage("Not editable", "To edit the user should be of class Customer");
                            t.getTableView().getItems().get(
                                    t.getTablePosition().getRow()).setPhone("");
                        }

                    }
            );
            nameCol.setCellValueFactory(new PropertyValueFactory<UsersTableParams, String>("name"));
            nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
            nameCol.setOnEditCommit(
                    t -> {
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setName(t.getNewValue());
                        User user = userHibController.getUserById(Integer.parseInt(t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).getId()));
                        if (user.getClass() == Person.class){
                            Person person = (Person) user;
                            person.setName(t.getTableView().getItems().get(
                                    t.getTablePosition().getRow()).getName());
                            userHibController.updateUser(person);
                        }
                        else {
                            AlertMessage.generateMessage("Not editable", "To edit the user should be of class Person");
                            t.getTableView().getItems().get(
                                    t.getTablePosition().getRow()).setName("");
                        }

                    }
            );
            surnameCol.setCellValueFactory(new PropertyValueFactory<UsersTableParams, String>("surname"));
            surnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
            surnameCol.setOnEditCommit(
                    t -> {
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setSurname(t.getNewValue());
                        User user = userHibController.getUserById(Integer.parseInt(t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).getId()));
                        if (user.getClass() == Person.class){
                            Person person = (Person) user;
                            person.setSurname(t.getTableView().getItems().get(
                                    t.getTablePosition().getRow()).getSurname());
                            userHibController.updateUser(person);
                        }
                        else {
                            AlertMessage.generateMessage("Not editable", "To edit the user should be of class Person");
                            t.getTableView().getItems().get(
                                    t.getTablePosition().getRow()).setSurname("");
                        }
                    }
            );
            companyNameCol.setCellValueFactory(new PropertyValueFactory<UsersTableParams, String>("companyName"));
            companyNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
            companyNameCol.setOnEditCommit(
                    t -> {
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setCompanyName(t.getNewValue());
                        User user = userHibController.getUserById(Integer.parseInt(t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).getId()));
                        if (user.getClass() == LegalEntity.class){
                            LegalEntity legalEntity = (LegalEntity) user;
                            legalEntity.setCompanyName(t.getTableView().getItems().get(
                                    t.getTablePosition().getRow()).getCompanyName());
                            userHibController.updateUser(legalEntity);
                        }
                        else {
                            AlertMessage.generateMessage("Not editable", "To edit the user should be of class LegalEntity");
                            t.getTableView().getItems().get(
                                    t.getTablePosition().getRow()).setCompanyName("");
                        }
                    }
            );

            Callback<TableColumn<UsersTableParams, Void>, TableCell<UsersTableParams, Void>> cellFactory
                    = new Callback<TableColumn<UsersTableParams, Void>, TableCell<UsersTableParams, Void>>() {
                @Override
                public TableCell<UsersTableParams, Void> call(final TableColumn<UsersTableParams, Void> param) {
                    final TableCell<UsersTableParams, Void> cell = new TableCell<UsersTableParams, Void>() {

                        private final Button button = new Button("Delete");

                        {
                            button.setOnAction((ActionEvent event) -> {
                                UsersTableParams data = getTableView().getItems().get(getIndex());
                                userHibController.removeUser(Integer.parseInt(data.getId()));
                                try {
                                    refreshTable();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            });
                        }

                        @Override
                        public void updateItem(Void item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                            } else {
                                setGraphic(button);
                            }
                        }
                    };
                    return cell;
                }
            };

            actionColBtn.setCellFactory(cellFactory);

            try {
                refreshTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        //Book List actions
        {
            class XCell extends ListCell<String> {
                HBox hbox = new HBox();
                Label label = new Label("(empty)");
                Pane pane = new Pane();
                Button button = new Button("(add to cart)");
                String lastItem;

                public XCell() {
                    super();
                    hbox.getChildren().addAll(label, pane, button);
                    HBox.setHgrow(pane, Priority.ALWAYS);
                    button.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            User user = userHibController.getUserById(userId);
                            int book_id = Integer.parseInt(lastItem.split(":")[0]);
                            Book book = bookHibController.getBookById(book_id);

                            /*ShopingCart shopingCart = new ShopingCart(user, book);
                            user.getMyOwnOrders().add(shopingCart);
                            book.getInCarts().add(shopingCart);
                            bookHibController.editBook(book);
                            userHibController.updateUser(user);*/

                            ShopingCart shopingCart = new ShopingCart(user, book);
                            book.getInCarts().add(shopingCart);
                            user.getMyOwnOrders().add(shopingCart);
                            cartHibController.createCart(shopingCart);

                        }
                    });
                }

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(null);
                    if (empty) {
                        lastItem = null;
                        setGraphic(null);
                    } else {
                        lastItem = item;
                        label.setText(item!=null ? item : "<null>");
                        setGraphic(hbox);
                    }
                }
            }

            bookList.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
                @Override
                public ListCell<String> call(ListView<String> param) {
                    return new XCell();
                }
            });
        }

    }

    private void modifyAccess() {
        User user = userHibController.getUserById(userId);
        if (user.getClass() != Employee.class) {
            ManageBooksTab.setDisable(true);
            ManageUsersTab.setDisable(true);
        } else {
            ManageBooksTab.setDisable(false);
            ManageUsersTab.setDisable(false);
        }
    }

    public void addBook() {
        if (bookPrice.getText().isBlank() || bookTitle.getText().isBlank() || bookAuthor.getText().isBlank()
                || bookDescription.getText().isBlank() || publDate.getValue() == null || pgNum.getText().isBlank()
                || bookLang.getValue() == null || bookQuantity.getText().isBlank() || bookGenre.getValue() == null) {
            AlertMessage.generateMessage("input error", "All fields should be non-empty");
            return;
        }
        Book book = new Book(Double.parseDouble(bookPrice.getText()), bookTitle.getText(),
                bookAuthor.getText(), bookDescription.getText(), publDate.getValue(), Integer.parseInt(pgNum.getText()),
                eBookLang.valueOf(bookLang.getSelectionModel().getSelectedItem().toString()), Integer.parseInt(bookQuantity.getText()),
                eBookGenre.valueOf(bookGenre.getSelectionModel().getSelectedItem().toString()));
        bookHibController.createBook(book);
        refreshBookLists(bookHibController.getAllBooks(false));
    }

    private void refreshBookLists(List<Book> books) {
        bookList.getItems().clear();
        bookListMngr.getItems().clear();
        books.forEach(b -> bookList.getItems().add(b.getProductID() + ": " + b.getName() + " by " + b.getAuthor()));
        books = bookHibController.getAllBooks(false);
        books.forEach(b -> bookListMngr.getItems().add(b.getProductID() + ": " + b.getName() + " by " + b.getAuthor() + "(Available: " + b.isAvailable() + ")"));
    }

    private void refreshTable() throws SQLException {
        usersTable.getItems().clear();
        usersTable.setEditable(true);

        List<Employee> employeeList = userHibController.getAllEmployees();
        for (Employee employee : employeeList) {
            UsersTableParams empTableParams = new UsersTableParams();
            empTableParams.setId(String.valueOf(employee.getId()));
            empTableParams.setUserType(employee.getClass().getSimpleName());
            empTableParams.setLogin(employee.getLogin());
            empTableParams.setCreateDate(employee.getCreateDate().toString());

            empTableParams.setPos(employee.getPos().toString());
            data.add(empTableParams);
        }

        List<Person> personList = userHibController.getAllPersons();
        for (Person person : personList) {
            UsersTableParams empTableParams = new UsersTableParams();
            empTableParams.setId(String.valueOf(person.getId()));
            empTableParams.setUserType(person.getClass().getSimpleName());
            empTableParams.setLogin(person.getLogin());
            empTableParams.setCreateDate(person.getCreateDate().toString());

            empTableParams.setEmail(person.getEmail());
            empTableParams.setPhone(person.getPhone());
            empTableParams.setName(person.getName());
            empTableParams.setSurname(person.getSurname());
            data.add(empTableParams);
        }

        List<LegalEntity> legalEntities = userHibController.getAllLegalEntities();
        for (LegalEntity legalEntity  : legalEntities) {
            UsersTableParams empTableParams = new UsersTableParams();
            empTableParams.setId(String.valueOf(legalEntity.getId()));
            empTableParams.setUserType(legalEntity.getClass().getSimpleName());
            empTableParams.setLogin(legalEntity.getLogin());
            empTableParams.setCreateDate(legalEntity.getCreateDate().toString());

            empTableParams.setEmail(legalEntity.getEmail());
            empTableParams.setPhone(legalEntity.getPhone());

            empTableParams.setCompanyName(legalEntity.getCompanyName());
            data.add(empTableParams);
        }

        usersTable.setItems(data);
    }

    public void setUserId(int userId) {
        this.userId = userId;
        modifyAccess();
    }

    public void openAddUserPage(ActionEvent actionEvent) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartController.class.getResource("../view/addUserPage.fxml"));
        Parent parent = fxmlLoader.load();

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("User Management");
        stage.setScene(scene);
        stage.showAndWait();

        refreshTable();
    }

    public void editSelected(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartController.class.getResource("../view/editBookPage.fxml"));
        Parent parent = fxmlLoader.load();

        EditBookPage editBookPage = fxmlLoader.getController();
        int id = Integer.parseInt(bookListMngr.getSelectionModel().getSelectedItem().toString().split(":")[0]);
        editBookPage.setBookData(id);

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Edit Book");
        stage.setScene(scene);
        stage.showAndWait();

        refreshBookLists(bookHibController.getAllBooks(false));

    }

    public void deleteSelected(ActionEvent actionEvent) {
        int id = Integer.parseInt(bookListMngr.getSelectionModel().getSelectedItem().toString().split(":")[0]);
        bookHibController.removeBook(id);
        refreshBookLists(bookHibController.getAllBooks(false));
    }

    public void createComment(ActionEvent actionEvent) {
        int id = Integer.parseInt(bookList.getSelectionModel().getSelectedItem().toString().split(":")[0]);
        Book book = bookHibController.getBookById(id);

        TextInputDialog dialog = new TextInputDialog("enter comment text");
        dialog.setTitle(book.getName());
        dialog.setHeaderText("Say something good about this book:");
        dialog.setContentText("");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            System.out.println("Your name: " + result.get());
            Comment bookComment = new Comment(result.get(), book);
            book.getComments().add(bookComment);
            bookHibController.editBook(book);
        }
    }

    public void viewComments(ActionEvent actionEvent) throws IOException {
        int id = Integer.parseInt(bookList.getSelectionModel().getSelectedItem().toString().split(":")[0]);
        FXMLLoader fxmlLoader = new FXMLLoader(LoginPage.class.getResource("../view/CommentsPage.fxml"));
        Parent parent = fxmlLoader.load();
        CommentsPage commentsPage = fxmlLoader.getController();
        commentsPage.setBookId(id);
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }


    public void filterBooks() {
        List<Book> books = bookHibController.getFilteredBooks(languageCmb.getValue().toString(), genreCmb.getValue().toString(),
                priceFromF.getText(), priceToF.getText());
        refreshBookLists(books);
    }

    public void ConfirmOrderBtn(ActionEvent actionEvent) {
    }
}
