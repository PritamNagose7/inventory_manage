document.addEventListener("DOMContentLoaded", function() {

	// Fetch and display suppliers
	fetch('/api/suppliers')
		.then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.json();
		})
		.then(data => {
			const supplierTable = document.getElementById('supplierTable').getElementsByTagName('tbody')[0];
			data.forEach(supplier => {
				const row = supplierTable.insertRow();
				row.insertCell(0).textContent = supplier.id;
				row.insertCell(1).textContent = supplier.name;
				row.insertCell(2).textContent = supplier.contactInfo;
				row.insertCell(3).innerHTML = `<button onclick="deleteSupplier(${supplier.id})">Delete</button>`;
			});
		})
		.catch(error => console.error('Error fetching suppliers:', error));


	// Fetch and display products
	/*const productTableBody = document.getElementById('product-data');
	if (productTableBody) {
		fetch('/api/products')
			.then(response => response.json())
			.then(products => {
				products.forEach(product => {
					const row = document.createElement('tr');
					row.innerHTML = `
                        <td>${product.id}</td>
                        <td>${product.description}</td>
                        <td>${product.name}</td>
                        <td>₹${product.price.toFixed(2)}</td>
                        <td>${product.shiftManagerId}</td>
                        <td>${product.shiftManagerName}</td>
                        <td>${product.stock}</td>
                        <td>${product.stockExportDate}</td>
                        <td>${product.stockImportDate}</td>
                        <td>${product.supplierId}</td>
                        <td>
                            <button onclick="updateProduct(${product.id})">Update</button>
                            <button onclick="deleteProduct(${product.id})">Delete</button>
                        </td>
                    `;
					productTableBody.appendChild(row);
				});
			})
			.catch(error => console.error('Error fetching products:', error));
	}*/



// Fetch and display products
const productTableBody = document.getElementById('product-data');
if (productTableBody) {
    fetch('/api/products')
        .then(response => response.json())
        .then(products => {
            console.log('Products:', products); // Log the entire response to debug
            products.forEach(product => {
                console.log('Product:', product); // Log each product to check the structure
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${product.id}</td>
                    <td>${product.description}</td>
                    <td>${product.name}</td>
                    <td>₹${product.price ? product.price.toFixed(2) : 'N/A'}</td>
                    <td>${product.shiftManagerId || 'N/A'}</td>
                    <td>${product.shiftManagerName || 'N/A'}</td>
                    <td>${product.stock || 'N/A'}</td>
                    <td>${product.stockExportDate || 'N/A'}</td>
                    <td>${product.stockImportDate || 'N/A'}</td>
                    <td>${product.supplierId || 'N/A'}</td>
                    <td>
                        <button onclick="updateProduct(${product.id})">Update</button>
                        <button onclick="deleteProduct(${product.id})">Delete</button>
                    </td>
                `;
                productTableBody.appendChild(row);
            });
        })
        .catch(error => console.error('Error fetching products:', error));
}







	// Add Product
	const addProductForm = document.getElementById('addProductForm');
	if (addProductForm) {
		addProductForm.addEventListener('submit', function(event) {
			event.preventDefault();
			const formData = new FormData(addProductForm);
			const product = {
				name: formData.get('name'),
				description: formData.get('description'),
				price: parseFloat(formData.get('price')),
				stock: parseInt(formData.get('stock')),
				stockImportDate: formData.get('stockImportDate'),
				stockExportDate: formData.get('stockExportDate'),
				shiftManagerName: formData.get('shiftManagerName'),
				shiftManagerId: parseInt(formData.get('shiftManagerId')),
				supplier: { id: parseInt(formData.get('supplier')) }
			};
			fetch('/api/products', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json'
				},
				body: JSON.stringify(product)
			})
				.then(response => {
					if (!response.ok) {
						throw new Error('Network response was not ok');
					}
					return response.json();
				})
				.then(data => {
					alert('Product added successfully!');
					window.location.href = 'index.html';
				})
				.catch(error => console.error('Error adding product:', error));
		});
	};


	// Add Supplier
	const addSupplierForm = document.getElementById('addSupplierForm');
	if (addSupplierForm) {
		addSupplierForm.addEventListener('submit', function(event) {
			event.preventDefault();
			const formData = new FormData(addSupplierForm);
			const supplier = {
				name: formData.get('name'),
				contactInfo: formData.get('contactInfo')
			};
			fetch('/api/suppliers', {
				method: 'POST',
				headers: { 'Content-Type': 'application/json' },
				body: JSON.stringify(supplier)
			})
				.then(response => {
					if (!response.ok) {
						throw new Error('Network response was not ok');
					}
					return response.json();
				})
				.then(data => {
					alert('Supplier added successfully!');
					window.location.href = 'addproduct.html';
				})
				.catch(error => console.error('Error adding supplier:', error));
		});
	}

});


// delete product function
function deleteProduct(id) {
	fetch(`/api/products/${id}`, {
		method: 'DELETE'
	})
		.then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			alert('Product deleted successfully!');
			window.location.reload();
		})
		.catch(error => console.error('Error deleting product:', error));
}


// delete supplier function 
function deleteSupplier(id) {
	fetch(`/api/suppliers/${id}`, {
		method: 'DELETE'
	})
		.then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			alert('Supplier deleted successfully!');
			window.location.reload();
		})
		.catch(error => console.error('Error deleting supplier:', error));
}
