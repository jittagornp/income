/**
 * Faces Validator
 */
PrimeFaces.validator = PrimeFaces.validator || {};

PrimeFaces.validator['emailValidator'] = {
    pattern: /\S+@\S+/,
    validate: function(element, value) {
        alert(5);
        if (!this.pattern.test(value)) {
            throw {
                summary: 'Validation Error',
                detail: value + ' is not a valid email.'
            }
        }
    }
};