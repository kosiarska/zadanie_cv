var Localize = require("localize-with-spreadsheet");
var transformer = Localize.fromGoogleSpreadsheet("xxx", '*');
transformer.setKeyCol('name');

transformer.save("../app/src/main/res/values/strings.xml", { valueCol: "English", format: "android" });