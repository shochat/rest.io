const lengthRange = document.getElementById("passwordLengthRange");
const lengthNumber = document.getElementById("passwordLengthNumber");

lengthNumber.addEventListener('input', syncAmount);
lengthRange.addEventListener('input', syncAmount);

function syncAmount(event) {
    const value = event.target.value;
    lengthRange.value = value;
    lengthNumber.value = value;
}