const togglePassword = document.querySelector('#togglePassword');
const password = document.querySelector('#password');

togglePassword.addEventListener('click', function () {
    // Alterna entre mostrar y ocultar la contraseña
    const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
    password.setAttribute('type', type);

    // Cambia el ícono
    this.classList.toggle('bi-eye');
    this.classList.toggle('bi-eye-slash');
});