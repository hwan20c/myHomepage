const main = {
  init: function() {
    const _this = this;
    $('#btn-remove').on('click', function() {
      _this.delete();
    })
  },

  delete: function() {
    const base_request_url = _ctx + '/myBoard';
    const id = $('#btn-remove').val();

    $.ajax({
      type: 'DELETE',
      url: base_request_url + '/' + id,
      headers: {
        'X-CSRF-TOKEN': token
      },
      async: false
    }).done(function() {
      window.location.href = base_request_url;
    }).fail(function(error) {
      console.log('[ Error Response ]\n' + error)
    });
  }
};

main.init();