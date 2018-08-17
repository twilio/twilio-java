package com.twilio.type;

// imports
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;


// class
class OutboundCallPriceWithOrigin = {

        ///////////////////////////////////////
        //members
        ///////////////////////////////////////

        // public double BasePrice = {}                         Mistake: just declaring variable (vs. csharp did assignment with getter function (must be shorthand sytnax)
        // private double BasePrice;                            Syntax Mistake: camelCase (not PascalCase)
        private final double basePrice;                         // final keyword? ANS: modifier (makes a variable a constant, meaning value can't be changed) https://www.geeksforgeeks.org/final-keyword-java/
        private final double currentPrice;
        private final List<String> originationPrefixes;

        ///////////////////////////////////////
        // GETTERS
        ///////////////////////////////////////

//        public double getBasePrice = {                        Syntax Mistake: not an object. Here your'e assigning getBasePrice to be an object
//            return this.basePrice;
//        }

//        public double getBasePrice() {
//            return this.basePrice;                            Syntax Mistake: don't access members with "this" keyword
//        }

        public double getBasePrice() { return basePrice; }
        public double getCurrentPrice() { return currentPrice; }
        public List<String> getOriginationPrefixes() { return originationPrefixes; }

        ///////////////////////////////////////
        //CONSTRUCTOR
        ///////////////////////////////////////

//        public OutboundCallPriceWithOrigin(double BasePrice, double CurrentPrice, List<String> OriginationPrefixes) {     Mistake: need to include final here (why?). It's like part of type (so it wouldn't accept just a double? if parameter was double, would it reject final double type?
//                                                                                                                          Mistake: JsonProperty (what is this doing?) ANS: it's getting the parameters from a JSON (since response comes from API), so JsonProperty is telling what constructor what the JSON key's name is (https://stackoverflow.com/questions/12583638/when-is-the-jsonproperty-property-used-and-what-is-it-used-for)
//            basePrice = BasePrice;                                                                                        Syntax Mistake: needs "this" keyword (SO CONSTRUCTOR USES "this", BUT MEMBER VARIABLES DON'T

//        public OutboundCallPriceWithOrigin (@JsonProperty("base_price") basePrice,) {                                       Syntax Mistake: even with @JsonProperty annotation, you need parameter type
        public OutboundCallPriceWithOrigin(@JsonProperty("base_price") final double basePrice,
                                           @JsonProperty("current_price") final double currentPrice,
                                           @JsonProperty("origination_prefixes") final List<String> origination_prefixes) {
            this.basePrice = basePrice;
            this.currentPrice = currentPrice;
            this.originationPrefixes = originationPrefixes;
        }

        ///////////////////////////////////////
        //FUNCTION OVERRIDES (toString, hashCode, equals)
        ///////////////////////////////////////

        @Override                                                 // tells compiler that you're overriding the method in the superclass
        public string toString() {
            return MoreObjects.toStringHelper(this)                                   // how do I pull in imports? So I can get code completion help from IDE?
                    .add("base_price", this.basePrice)
                    .add("current_price", this.currentPrice)
                    .add("origination_prefixes", this.originationPrefixes)
                    .toString();
        }

        @Override
        public int hashCode() {
            return MoreObjects.hashCode(this.basePrice, this.currentPrice, this.originationPrefixes)
        }

        @Override
        public bool equals(Object o) {
            // IF "this" and "o" point to the same object, return true
//            if (this == o)                                                        // SYNTAX MISTAKE: wrap conditionals in curly braces
//                return true;

            if (this == o) {
                return true;
            }


            // IF "o" is null or "o" isn't of type OutboundCallPriceWithOrigin, return false
//            else if (o == null || o.getClass() != this.getClass())                // SYNTAX MISTAKE: "this" keyword not needed in "this.getClass()". Would it cause error if I used it?
//                return false;

            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            // COMPARE EACH PROPERTY
                                                                                    // MISTAKE: create another object "other" by casting "o" to type OutboundCallPriceWithOrigin (how does the compiler do this?). This prevents error of trying to access key that may not exists on "o". Learning Java snippet workflow (worksheets in IntelliJ?)
//            else
//                return Objects.equal(this.basePrice, o.basePrice) &&

            OutboundCallPriceWithOrigin other = (OutboundCallPriceWithOrigin) o;
            return Objects.equal(this.basePrice, other.basePrice) &&
                   Objects.equal(this.currentPrice, other.currentPrice) &&
                   Objects.equal(this.originationPrefixes, other.originationPrefixes)
        }
}