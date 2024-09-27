// Función para alternar la visibilidad de la contraseña
function togglePassword(id) {
    const passwordField = document.getElementById(id);
    const button = passwordField.nextElementSibling;
    if (passwordField.type === 'password') {
        passwordField.type = 'text';
        button.innerHTML = '<i class="bi bi-eye-slash"></i>';
    } else {
        passwordField.type = 'password';
        button.innerHTML = '<i class="bi bi-eye"></i>';
    }
}