// alert('테스트');
(function (path) {
  // 코드 작성
  $('.table').DataTable({
    language: {
      // 메뉴 한글로 변경
      lengthMenu: '표시할 줄수 선택    _MENU_',
      search: '검색',
      paginate: { previous: '이전', next: '다음' },
      info: '페이지 _PAGE_ / _PAGES_',
      infoEmpty: '데이터가 없습니다.',
      infoFiltered: '(전체 페이지 _MAX_ 에서 검색)',
      thousands: ',',
    },
    lengthMenu: [5, 10, 25], // 한 페이지에 표시할 행 수
    pageLength: 5, // 페이지 갯수
    ordering: false, // 정렬옵션 off
    stateSave: true,
  });
  // 추가: 추가버튼을 클릭 => 모달창을 선택해 제목을 바꿔줌
  $('.btn-add').click(function (e) {
    // 제이쿼리로 변수를 설정할땐 $로 시작한다 (제이쿼리로 선택한 객체)
    const $modal = $('#modal-add-update');
    // .find는 모달 내부의 title-add-upd를 찾는 작업 => 텍스트란을 수정해 제목을 만들어줌
    $modal.find('#title-add-upd').text('새 연락처');
    $modal.find('form').attr('action', path + '/contact?cmd=post');
  });
  // add, update의 submit 버튼을 클릭 => 추가 또는 업데이트 (Ajax사용)
  $('#add-update').on('submit', function (e) {
    e.preventDefault(); // submit 동작 중지
    e.stopPropagation(); // 이벤트 중지
    $('.btn-action').prop('disabled', true); // 모달창 닫기 중지

    // 새연락처 추가: ajax를 통해 요청 => json으로 데이터를 받아 출력
    $.ajax({
      type: 'POST',
      url: $('#add-update').attr('action'), // 여기서 action은 /contacts?cmd=post 를 의미함
      data: $('#add-update').serialize(), // form에 입력한 내용을 문자열로 변환
      // contentType속성은 보낼때 타입이지만 여기서는 기본형으로 보내므로 생략가능
      dataType: 'json', // 받을 때 타입
    }).done(function (data) {
      // 위의 작업이 성공적으로 완료되었을때 동작 (출력과정)
      if (data.status) {
        // data가 true이면 실행(= 요청결과를 성공적으로 받았을때)
        $('#modal-add-update').modal('hide'); // 모달창 닫기
        location.reload(); // 새로고침
        // console.log(data); // 새로고침하면 data가 사라지므로 reload를 안한상태에서 확인해야함
      }
    }); //ajax done
  });
  // 수정: 테이블에서 수정버튼 클릭시 모달창 열기(id로 해당 연락처 내용을 채움)
  $('table').on('click', '.btn-edit', function (e) {
    const $modal = $('#modal-add-update');
    $modal.find('#title-add-upd').text('업데이트');
    $modal.find('form').attr('action', path + '/contact?cmd=update');

    $.ajax({
      type: 'POST',
      url: path + '/contact?cmd=edit',
      data: 'id=' + $(this).data('id'), // 클릭한 객체의 id속성값을 넘겨받음
      dataType: 'json', // 받을 때 타입
    })
      .done(function (data) {
        console.log(data);
        if (data.status) {
          // data가 true이면 실행(= 요청결과를 성공적으로 받았을때)
          $('#name').val(data.contact.name);
          $('#email').val(data.contact.email);
          $('#phone').val(data.contact.phone);

          // 히든 타입의 id입력창을 넣는다. 이때 id도 입력됨.
          $modal.find('form').append('<input type="hidden" name="id" value="' + data.contact.id + '">');

          $modal.modal('show');
        }
      })
      .fail(function (jqXHR, textStatus) {
        console.log(textStatus);
      });
  });
  // 삭제: 테이블에서 삭제버튼 클릭 => 삭제 모달창 생성
  $('table').on('click', '.btn-delete', function (e) {
    $('#frm-delete').find('input[name=id]').val($(this).data('id')); // input name속성에 현재 클릭한 id를 입력함
  });
  // 삭제작업 (삭제모달의 form의 submit버튼을 클릭했을 때)
  $('#frm-delete').submit(function (e) {
    e.preventDefault(); // submit 동작 중지
    e.stopPropagation(); // 이벤트 중지
    $('.btn-action').prop('disabled', true);

    $.ajax({
      type: 'POST',
      url: path + '/contact?cmd=del',
      data: $('#frm-delete').serialize(), // form태그 입력내용을 문자열로 변환
      dataType: 'json', // 받을 때 타입
    })
      .done(function (data) {
        if (data.status) {
          $('#modal-delete').modal('hide'); // 성공시 모달 창 닫기
          location.reload(); // 새로고침
        }
      })
      .fail(function (jqXHR, textStatus) {
        console.log(textStatus); // 실패시
      });
  });
})(path);
