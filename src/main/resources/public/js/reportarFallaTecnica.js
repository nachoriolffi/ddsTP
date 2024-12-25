document.addEventListener('DOMContentLoaded', function() {
    const uploadArea = document.getElementById('uploadArea');
    const fileInput = document.getElementById('fotoFalla');
    const imagePreview = document.getElementById('imagePreview');
    const previewImage = imagePreview.querySelector('img');
    const removeButton = document.getElementById('removeImage');
    const form = document.getElementById('fallaForm');

    uploadArea.addEventListener('click', () => fileInput.click());

    uploadArea.addEventListener('dragover', (e) => {
        e.preventDefault();
        uploadArea.style.borderColor = '#FA6900';
    });

    uploadArea.addEventListener('dragleave', () => {
        uploadArea.style.borderColor = '#ccc';
    });

    uploadArea.addEventListener('drop', (e) => {
        e.preventDefault();
        uploadArea.style.borderColor = '#ccc';
        if (e.dataTransfer.files.length) {
            fileInput.files = e.dataTransfer.files;
            updatePreview(e.dataTransfer.files[0]);
        }
    });

    fileInput.addEventListener('change', (e) => {
        if (fileInput.files.length) {
            updatePreview(fileInput.files[0]);
        }
    });

    removeButton.addEventListener('click', () => {
        fileInput.value = '';
        imagePreview.classList.add('d-none');
        uploadArea.classList.remove('d-none');
    });

    function updatePreview(file) {
        if (file) {
            const reader = new FileReader();
            reader.onload = (e) => {
                previewImage.src = e.target.result;
                imagePreview.classList.remove('d-none');
                uploadArea.classList.add('d-none');
            };
            reader.readAsDataURL(file);
        }
    }

    form.addEventListener('submit', (e) => {
        e.preventDefault();
        // Aquí puedes agregar la lógica para enviar el formulario
        alert('Formulario enviado con éxito!');
    });
});