const cardCategoryInput = document.getElementById('cards');
const balancesCardDiv = document.getElementById('balancesCard');
const formcard = document.getElementById('form_card');

cardCategoryInput.addEventListener('change', () => {
    if (cardCategoryInput.value === 'Кредитная') {
        balancesCardDiv.style.opacity = '1';
        balancesCardDiv.style.transform = 'translateY(0)';
    } else {
        balancesCardDiv.style.opacity = '0';
        balancesCardDiv.style.transform = 'translateY(20px)';
    }
});
