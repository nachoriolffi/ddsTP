(function () {
    'use strict';

    var forms = document.querySelectorAll('.needs-validation');

    Array.prototype.slice.call(forms)
        .forEach(function (form) {
            form.addEventListener('submit', function (event) {
                var fileInput = document.getElementById('csv-file');
                var file = fileInput.files[0];
                var fileIsValid = file && file.name.endsWith('.csv');

                if (!fileIsValid) {
                    event.preventDefault();
                    event.stopPropagation();
                    fileInput.classList.add('is-invalid');
                    fileInput.classList.remove('is-valid');
                } else {
                    fileInput.classList.remove('is-invalid');
                    fileInput.classList.add('is-valid');
                    form.classList.add('was-validated');
                }
            }, false);
        });
})();