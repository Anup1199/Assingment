document.addEventListener('DOMContentLoaded', function () {
    const pages = document.querySelectorAll('.page');

    document.getElementById('home').style.display = 'block';

    document.getElementById('nav-home').addEventListener('click', function () {
        showPage('home');
    });
    document.getElementById('nav-about').addEventListener('click', function () {
        showPage('about');
    });
    document.getElementById('nav-contact').addEventListener('click', function () {
        showPage('contact');
    });

    function showPage(pageId) {
        pages.forEach(function (page) {
            if (page.id === pageId) {
                page.style.display = 'block';
            } else {
                page.style.display = 'none';
            }
        });
    }
});
