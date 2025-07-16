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

$(document).ready(function () {
    updateUnitOptions("length");

    $(".tab-btn").click(function () {
        $(".tab-btn").removeClass("active");
        $(this).addClass("active");
        const selected = $(this).data("category");
        $("#category").val(selected);
        updateUnitOptions(selected);
    });

    $("#convertForm").on("submit", function (e) {
        e.preventDefault();
        const category = $("#category").val();
        const from = $("#fromUnit").val();
        const to = $("#toUnit").val();
        const value = parseFloat($("#value").val());

        $.post("/api/convert", { category, from, to, value }, function (data) {
            $("#resultText").text(`${value} ${from} = ${data.toFixed(3)} ${to}`);
        }).fail(() => {
            $("#resultText").text("Conversion error!");
        });
    });

    $("#resetBtn").click(() => {
        $("#value").val('');
        $("#resultText").text('--');
    });
});
