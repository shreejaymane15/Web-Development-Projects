// Invoke Functions Call on Document Loaded
document.addEventListener('DOMContentLoaded', function () {
  hljs.highlightAll();
});


document.addEventListener('DOMContentLoaded', function() {
  let alertWrapper = document.querySelector('.alert');
  let alertClose = document.querySelector('.alert__close');

  if (alertWrapper != null) {
      alertClose.addEventListener('click', function(e) {
          e.preventDefault();
          alertWrapper.style.display = 'none';
      });
    }
});