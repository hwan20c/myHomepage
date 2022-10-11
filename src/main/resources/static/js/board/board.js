const main = {
  init: function() {
    const _this = this;
    $('#btn-search').on('click', function() {
      _this.search();
    });

    $('#searchValue').on('keydown', function(key) {
      if(key.keyCode === 13) {
        _this.search();
      }
    });
  },

  search: function() {
    const searchKey = $('#searchKey').val();
    const searchValue = $('#searchValue').val();
    window.location.href = searchUrl + '?searchKey=' + searchKey + '&searchValue=' + searchValue;
  }
}
main.init();