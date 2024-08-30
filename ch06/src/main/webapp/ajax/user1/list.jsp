<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ajax::user1</title>
	<script>
		window.onload = function(){
			
			// 이런 거 연습해야 함 JavaScript 코딩이 훨씬 많음
			const table = document.getElementsByTagName('table')[0];
			
			// 사용자 데이터 요청
			fetch('./proc/getUsers.jsp')
				.then(response => response.json())
				.then(data => {
					console.log(data);
					
					for(const user of data){
						console.log(user.uid);
						
						const row = `<tr>
										<td>\${user.uid}</td>
										<td>\${user.name}</td>
										<td>\${user.birth}</td>
										<td>\${user.hp}</td>
										<td>\${user.age}</td>
										<td>
											<a href='#' class='modify'>수정</a>
											<a href='#' class='delete'>삭제</a>
										</td>
									</tr>`;
						/*
						let row = "<tr>"
							row += "<td>"+user.uid+"</td>"
							row += "<td>"+user.name+"</td>"
							row += "<td>"+user.birth+"</td>"
							row += "<td>"+user.hp+"</td>"
							row += "<td>"+user.age+"</td>"
							row += "<td>"
							row += "<a href='#'>수정</a>"
							row += "<a href='#'>삭제</a>"
							row += "</td>"
							row += "</tr>";
						*/
						console.log(user.uid);	
						table.insertAdjacentHTML('beforeend', row);
				}
					
				})
				.catch(err => {
					console.log(err);
				});
			
			
			document.body.addEventListener('click', function(e){
				
				
				// 버블링 효과를 통해서 삭제를 하면 타켓이 delete이면 ~
				// 삭제 클릭 이벤트 (동적 이벤트 연결을 위해 body 태그에 이벤트 처리를 위임)
				if(e.target.classList.contains('delete')){
					e.preventDefault();
					//alert('삭제');
					
					 const tr = e.target.closest('tr');
					 //tr.remove();
					 
					 const uid = tr.children[0].innerText;
					 //alert(uid); 삭제할 아이디 값 alert 됨
					 
					 // 삭제 요청
					 fetch('./proc/deleteProc.jsp?uid='+uid)
						 .then(response => response.json())
						 .then(data => {
							 console.log(data);
							 if(data.result > 0){
								 
								 alert('삭제 했습니다.');
								 tr.remove();
							 }
						 })
						 .catch(err => {
							 console.log(err);
						 });
					 
					 
				}
					 
					 // 수정 클릭 이벤트 
					 if(e.target.classList.contains('modify')){
						 e.preventDefault();
						 const tr = e.target.closest('tr');
						 const uid = tr.children[0].innerText;
						 
						 location.href = './modify.jsp?uid='+uid;
					  
				}
					 
					 
				
			});
			/*
			// 이거는 동작 안함 
			//const btnDelete = document.getElementsByClassName('delete');
			const btnDelete = document.querySelectorAll('.delete');
			btnDelete.forEach((tag)=>{
				tag.onclick = function(){
					alert('click!');
				}
			});
			*/
			
			
		}// onload ends
	</script>
</head>
<body>
	<h3>user1 목록</h3>
	
	<a href="./register.jsp">등록하기</a>
	<table border="1">
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>생년월일</th>
			<th>휴대폰</th>
			<th>나이</th>
			<th>관리</th>
		</tr>
		<!-- 이 자리에 태그 생성해야 함  -->
		
		
	</table>
	
</body>
</html>