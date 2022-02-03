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
  // 추가버튼을 클릭 => 모달창을 선택해 제목을 바꿔줌
  $('.btn-add').click(function (e) {
    // 제이쿼리로 변수를 설정할땐 $로 시작한다 (제이쿼리로 선택한 객체)
    const $modal = $('#modal-add-update');
    // .find는 모달 내부의 title-add-upd를 찾는 작업 => 텍스트란을 수정해 제목을 만들어줌
    $modal.find('#title-add-upd').text('새 연락처');
    $modal.find('form').attr('action', path + '/contact?cmd=post');
  });
})(path);
