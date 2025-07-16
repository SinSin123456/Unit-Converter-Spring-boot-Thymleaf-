const units = {
    length: ["millimeter", "centimeter", "meter", "kilometer", "inch", "foot", "yard", "mile"],
    weight: ["milligram", "gram", "kilogram", "ounce", "pound"],
    temperature: ["Celsius", "Fahrenheit", "Kelvin"]
};

function updateUnitOptions(category) {
    const from = $("#fromUnit");
    const to = $("#toUnit");
    from.empty(); to.empty();

    units[category].forEach(unit => {
        from.append(new Option(unit, unit.toLowerCase()));
        to.append(new Option(unit, unit.toLowerCase()));
    });
}

$("#category").on("change", function () {
    updateUnitOptions($(this).val());
});

$("#convertForm").on("submit", function (e) {
    e.preventDefault();
    const category = $("#category").val();
    const from = $("#fromUnit").val();
    const to = $("#toUnit").val();
    const value = parseFloat($("#value").val());

    $.post("/api/convert", { category, from, to, value }, function (data) {
        $("#result").text(data.toFixed(4));
    }).fail(() => {
        $("#result").text("Error in conversion.");
    });
});

// Default load
$(document).ready(function () {
    updateUnitOptions("length");
});
