<html>
<head>
    <title></title>
    <link href="${pageContext.request.contextPath}/resources/styles/kendo.common.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/resources/styles/kendo.black.min.css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/kendo.web.min.js"></script>
</head>
<body class="k-content">
<div id="example">
    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Units</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody data-template="row-template" data-bind="source: products"></tbody>
        <tfoot data-template="footer-template" data-bind="source: this"></tfoot>
    </table>

    <div>
        <span>Add a product</span>
        <ul>
            <li>
                <label>Name:<input type="text" placeholder="Enter name" data-bind="value: productName" class="k-input"/></label>
            </li>
            <li>
                <label>Price:<input type="number" placeholder="Enter number" data-bind="value: productPrice"/></label>
            </li>
            <li>
                <label>Units in stock:<input type="number" placeholder="Enter number"
                                             data-bind="value: productUnitsInStock"/></label>
            </li>
            <li>
                <button data-bind="click: addProduct" class="k-button">Add a new product</button>
            </li>
        </ul>
    </div>
</div>
<script id="row-template" type="text/x-kendo-template">
    <tr>
        <td data-bind="text: name">
        </td>
        <td>
            #: kendo.toString(get("price"), "C") #
        </td>
        <td data-bind="text: unitsInStock"></td>
        <td>
            <button data-bind="click: deleteProduct">Delete</button>
        </td>
    </tr>
</script>
<script id="footer-template" type="text/x-kendo-template">
    <tr>
        <td>
            Products count: #: total() #
        </td>
        <td>
            Total price: #: totalPrice() #
        </td>
        <td colspan="2">
            Units in stock: #: totalUnitsInStock() #
        </td>
    </tr>
</script>
<script>
    var viewModel = kendo.observable({
        productName: "Product name",
        productPrice: 10,
        productUnitsInStock: 10,
        addProduct: function () {
            this.get("products").push({
                name: this.get("productName"),
                price: parseFloat(this.get("productPrice")),
                unitsInStock: parseFloat(this.get("productUnitsInStock"))
            });
        },
        deleteProduct: function (e) {
            // the current data item (product) is passed as the "data" field of the event argument
            var product = e.data;

            var products = this.get("products");

            var index = products.indexOf(product);

            // remove the product by using the splice method
            products.splice(index, 1);
        },
        total: function () {
            return this.get("products").length;
        },
        totalPrice: function () {
            var sum = 0;

            $.each(this.get("products"), function (index, product) {
                sum += product.price;
            });

            return sum;
        },
        totalUnitsInStock: function () {
            var sum = 0;

            $.each(this.get("products"), function (index, product) {
                sum += product.unitsInStock;
            });

            return sum;
        },
        products: [
            { name: "Hampton Sofa", price: 989.99, unitsInStock: 39 },
            { name: "Perry Sofa", price: 559.99, unitsInStock: 17 },
            { name: "Donovan Sofa", price: 719.99, unitsInStock: 29 },
            { name: "Markus Sofa", price: 839.99, unitsInStock: 3 }
        ]
    });

    kendo.bind($("#example"), viewModel);
    kendo.init($("#example"));
</script>
</body>
</html>