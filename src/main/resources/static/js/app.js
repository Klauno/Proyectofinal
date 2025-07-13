const API_BASE = ''; // Cambia según tu backend

// --- Productos ---

async function fetchProducts() {
  try {
    const res = await fetch(`${API_BASE}/producto/list`);
    if (!res.ok) throw new Error('Error al cargar productos');
    const products = await res.json();
    displayProducts(products);
  } catch (error) {
    console.error(error);
    const container = document.getElementById('product-list');
    if (container) {
      container.innerHTML = `<p>Error al cargar productos: ${error.message}</p>`;
    }
  }
}

function displayProducts(products) {
  const container = document.getElementById('product-list');
  if (!container) return;

  container.innerHTML = '';

  products.forEach(p => {
    const card = document.createElement('div');
    card.className = 'card';

    const descId = `desc-${p.id}`;

    card.innerHTML = `
      <h3>${p.nombre}</h3>
      <img src="${p.imagenUrl || 'https://via.placeholder.com/150'}" alt="${p.nombre}" />

      <button onclick="toggleDescription('${descId}')" class="desc-toggle-btn">Mostrar descripción</button>
      <p id="${descId}" class="product-description">${p.descripcion || 'Sin descripción disponible.'}</p>

      <p>Precio: $${p.precio.toFixed(2)}</p>
      <p>Stock: ${p.stock}</p>

      <div class="quantity-control">
        <button onclick="decrementQuantity(${p.id})">-</button>
        <input type="number" id="qty-${p.id}" value="1" min="1" max="${p.stock}" />
        <button onclick="incrementQuantity(${p.id}, ${p.stock})">+</button>
      </div>

      <button onclick="addToCartWithQuantity(${p.id}, ${p.precio}, ${p.stock}, '${p.nombre.replace(/'/g, "\\'")}')">Añadir al carrito</button>
    `;

    // Ocultar descripción inicialmente
    const tempDiv = document.createElement('div');
    tempDiv.innerHTML = card.innerHTML;
    const descParagraph = tempDiv.querySelector(`#${descId}`);
    if (descParagraph) descParagraph.style.display = 'none';
    card.innerHTML = tempDiv.innerHTML;

    container.appendChild(card);
  });
}

function toggleDescription(descId) {
  const desc = document.getElementById(descId);
  const btn = desc.previousElementSibling;

  if (desc.style.display === 'none' || desc.style.display === '') {
    desc.style.display = 'block';
    btn.textContent = 'Ocultar descripción';
  } else {
    desc.style.display = 'none';
    btn.textContent = 'Mostrar descripción';
  }
}

function incrementQuantity(productId, maxStock) {
  const input = document.getElementById(`qty-${productId}`);
  if (!input) return;
  let current = parseInt(input.value) || 1;
  if (current < maxStock) {
    input.value = current + 1;
  }
}

function decrementQuantity(productId) {
  const input = document.getElementById(`qty-${productId}`);
  if (!input) return;
  let current = parseInt(input.value) || 1;
  if (current > 1) {
    input.value = current - 1;
  }
}

function addToCartWithQuantity(id, precio, stock, nombre) {
  const input = document.getElementById(`qty-${id}`);
  let cantidad = 1;
  if (input) {
    cantidad = parseInt(input.value);
    if (isNaN(cantidad) || cantidad < 1) cantidad = 1;
    if (cantidad > stock) {
      alert(`No hay suficiente stock. Stock disponible: ${stock}`);
      return;
    }
  }

  let cart = JSON.parse(localStorage.getItem('cart')) || [];
  const index = cart.findIndex(item => item.id === id);
  if (index === -1) {
    cart.push({ id, precio, quantity: cantidad, available_quantity: stock, nombre });
  } else {
    if (cart[index].quantity + cantidad <= stock) {
      cart[index].quantity += cantidad;
    } else {
      alert('No hay suficiente stock disponible.');
      return;
    }
  }
  localStorage.setItem('cart', JSON.stringify(cart));
  alert(`Producto "${nombre}" agregado al carrito (${cantidad} unidad${cantidad > 1 ? 'es' : ''}).`);
  displayCart();
}

// --- Carrito ---

function displayCart() {
  const container = document.getElementById('cart-items-container');
  const totalEl = document.getElementById('total');
  if (!container || !totalEl) return;

  let cart = JSON.parse(localStorage.getItem('cart')) || [];
  container.innerHTML = '';

  if (cart.length === 0) {
    container.innerHTML = '<p>El carrito está vacío.</p>';
    totalEl.innerText = 'Total: $0.00';
    return;
  }

  let total = 0;
  cart.forEach(item => {
    const precio = Number(item.precio) || 0;
    const cantidad = Number(item.quantity) || 0;
    const disponible = Number(item.available_quantity) || 0;
    const itemTotal = precio * cantidad;
    total += itemTotal;

    const div = document.createElement('div');
    div.className = 'cart-item';
    div.id = `cart-item-${item.id}`;
    div.innerHTML = `
      <div class='card-info'>
        <h5>${item.nombre || "Producto sin nombre"}</h5>
        <p>Precio: $${precio.toFixed(2)}</p>
        <p>Cantidad: ${cantidad}</p>
        <p>Stock disponible: ${disponible - cantidad}</p>
        <p>Total: $${itemTotal.toFixed(2)}</p>
      </div>
      <div class='cart-actions'>
        <button onclick='addMore(${item.id})'>Agregar</button>
        <button onclick='removeOneFromCart(${item.id})'>Eliminar una unidad</button>
        <button onclick='removeFromCart(${item.id})'>Eliminar todo</button>
      </div>`;
    container.appendChild(div);
  });

  const iva = total * 0.21;
  const totalConIva = total + iva;
  totalEl.innerHTML = `
    <strong>Total sin IVA:</strong> $${total.toFixed(2)}<br>
    <strong>IVA (21%):</strong> $${iva.toFixed(2)}<br>
    <strong>Total a pagar:</strong> $${totalConIva.toFixed(2)}
  `;
}

function addMore(productId) {
  let cart = JSON.parse(localStorage.getItem('cart')) || [];
  const index = cart.findIndex(i => i.id === productId);
  if (index === -1) return;

  if (cart[index].quantity + 1 > cart[index].available_quantity) {
    alert('No hay suficiente stock disponible.');
    return;
  }

  cart[index].quantity += 1;
  localStorage.setItem('cart', JSON.stringify(cart));
  displayCart();
}

function removeOneFromCart(productId) {
  let cart = JSON.parse(localStorage.getItem('cart')) || [];
  const index = cart.findIndex(i => i.id === productId);
  if (index === -1) return;

  if (cart[index].quantity > 1) {
    cart[index].quantity -= 1;
  } else {
    if (!confirm(`Eliminar el producto "${cart[index].nombre}" del carrito?`)) return;
    cart.splice(index, 1);
  }

  localStorage.setItem('cart', JSON.stringify(cart));
  displayCart();
}

function removeFromCart(productId) {
  let cart = JSON.parse(localStorage.getItem('cart')) || [];
  const index = cart.findIndex(i => i.id === productId);
  if (index === -1) return;

  if (!confirm(`Eliminar completamente el producto "${cart[index].nombre}" del carrito?`)) return;
  cart = cart.filter(i => i.id !== productId);
  localStorage.setItem('cart', JSON.stringify(cart));
  displayCart();
}

document.getElementById('clear-cart')?.addEventListener('click', () => {
  if (!confirm('¿Quieres vaciar el carrito?')) return;
  localStorage.removeItem('cart');
  displayCart();
  alert('Carrito vaciado');
});

document.getElementById('buy-button')?.addEventListener('click', () => {
  let cart = JSON.parse(localStorage.getItem('cart')) || [];
  if (cart.length === 0) {
    alert('El carrito está vacío.');
    return;
  }
  document.getElementById('payment-methods').style.display = 'block';
});

document.getElementById('confirm-payment')?.addEventListener('click', async () => {
  const metodo = document.getElementById('payment-select').value;
  const nombre = document.getElementById('user-name')?.value.trim() || '';
  const email = document.getElementById('user-email')?.value.trim() || '';

  if (!nombre || !email) {
    alert('Por favor, ingresa tu nombre y correo electrónico.');
    return;
  }

  let cart = JSON.parse(localStorage.getItem('cart')) || [];
  if (cart.length === 0) {
    alert('El carrito está vacío.');
    return;
  }

  try {
    const resUser = await fetch(`${API_BASE}/usuario/`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ nombre, email })
    });
    if (!resUser.ok) throw new Error('Error al registrar usuario');
    const usuario = await resUser.json();

    const pedido = {
      usuarioId: usuario.id,
      lineas: cart.map(item => ({
        productoId: item.id,
        cantidad: item.quantity
      }))
    };

    const resPedido = await fetch(`${API_BASE}/pedido/`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(pedido)
    });
    if (!resPedido.ok) throw new Error('Error al crear pedido');
    const data = await resPedido.json();

    alert(`Pedido creado con éxito: ${data.message}\nMétodo de pago: ${metodo}`);
    localStorage.removeItem('cart');
    displayCart();
    document.getElementById('payment-methods').style.display = 'none';
  } catch (error) {
    alert('Error: ' + error.message);
  }
});

// Inicialización
window.addEventListener('load', () => {
  if (document.getElementById('product-list')) {
    fetchProducts();
  }
  if (window.location.pathname.includes('carrito.html')) {
    displayCart();
  }
});
