document.querySelectorAll(".add-to-cart").forEach(btn => {

    btn.addEventListener("click", function (e) {

        e.preventDefault();

        const product = {
            id: this.dataset.id,
            name: this.dataset.name,
            price: parseFloat(this.dataset.price),
            thumbnail: this.dataset.thumbnail,
            qty: parseInt(this.dataset.qty)
        };

        let cart = JSON.parse(localStorage.getItem("cart")) || [];

        let existing = cart.find(item => item.id === product.id);

        if (existing) {
            existing.qty++;
        } else {
            cart.push(product);
        }

        localStorage.setItem("cart", JSON.stringify(cart));

        // chuyển sang trang cart
        window.location.href = "/cart";
    });
});

function updateCartCount() {
    let cart = JSON.parse(localStorage.getItem("cart")) || [];

    let total = 0;
    cart.forEach(item => {
        // total += item.qty; // số lượng
        total += item.price * item.qty; // tổng tiền
    });

    document.querySelector(".badge").innerText = "$" + total;
}
