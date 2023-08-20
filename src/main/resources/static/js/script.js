const lengthRange = document.getElementById("passwordLengthRange");
const lengthNumber = document.getElementById("passwordLengthNumber");
const form = document.getElementById("passwordGeneratorForm");



const passwordElement = document.getElementById("passwordDisplay")
const valueElement = document.getElementById("value");
const keyElement = document.getElementById("key")
const includeUppercaseElement = document.getElementById("includeUppercase");
const includeSymbolsElement = document.getElementById("includeSymbols");

lengthNumber.addEventListener('input', syncAmount);

lengthRange.addEventListener('input', syncAmount);
form.addEventListener('submit', e => {
    e.preventDefault();
    const value = valueElement.value;
    const key = keyElement.value;
    const passwordLength = lengthNumber.value
    const includeUppercase = includeUppercaseElement.checked
    const includeSymbols = includeSymbolsElement.checked
    passwordElement.innerText = generatePassword(value, key, passwordLength, includeUppercase, includeSymbols);
})

async function generatePassword(value, key, passwordLength, includeUppercase, includeSymbols) {
    const urlTemplate = "http://localhost:8080/password/forValue?"
        + "value=" + value
        + "&key=" + key
        + "&length=" + passwordLength
        + "&u=" + includeUppercase
        + "&s=" + includeSymbols

    const response = await fetch(urlTemplate, {
        method: "GET",
        headers: {
            "Content-Type": "text/html"
        }
    });

    return response.text().then()
}

function syncAmount(event) {
    const value = event.target.value;
    lengthRange.value = value;
    lengthNumber.value = value;
}