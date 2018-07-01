export default function(file, callback) {
  if (!file || !/image\//.test(file.type)) return
  var fileReader = new FileReader()
  fileReader.onload = function() {
    callback(null, fileReader.result)
  }
  fileReader.onerror = function() {
    callback('oops, something went wrong.')
  }
  fileReader.readAsDataURL(file)
}
