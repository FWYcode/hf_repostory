$(document).ready(function () {
    var num, add, reduce, current ,select,pice,djian;
    pice = $('#pice');
    djian = pice.text();
    num = $("#num");
    add = $('#itemadd');
    reduce = $('#itemreduce');
    num.val(1);
    select = $('*');
    current = num.val();
    select.hover(function () {
        current = num.val();
        if (current >= 200) {
            add.css('cursor', 'not-allowed');
        }
        if (current <= 1) {
            reduce.css('cursor', 'not-allowed');
        }
        if (current > 1 && current < 200) {
            add.css('cursor', 'pointer');
            reduce.css('cursor', 'pointer');
        }
    })
    add.click(function () {
        if (current >= 1 && current < 200) {
            current++;
            num.val(current)
            pice.text(djian * current);
        }
    })
    reduce.click(function () {
        if (current > 1 && current <= 200) {
            current--;
            num.val(current);
            pice.text(djian * current);

        }
    })
})