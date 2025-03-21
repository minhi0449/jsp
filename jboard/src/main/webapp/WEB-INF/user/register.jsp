<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<script src="/jboard/js/validation.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	function postcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    //document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('zip').value = data.zonecode;
                document.getElementById("addr1").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("addr2").focus();
            }
        }).open();
    }
	
</script>
<main>
    <section class="register">
        <form action="/jboard/user/register.do" method="post">
            <table border="1">
                <caption>사이트 이용정보 입력</caption>
                <tr>
                    <td>아이디</td>
                    <td>
                        <input type="text" name="uid" placeholder="아이디 입력"/>
                        <button type="button" id="btnCheckUid"><img src="../images/chk_id.gif" alt=""></button>
                        <span class="resultId"></span>
                    </td>
                </tr>
                <tr>
                    <td>비밀번호</td>
                    <td>
                        <input type="password" name="pass1" placeholder="비밀번호 입력"/>                            
                    </td>
                </tr>
                <tr>
                    <td>비밀번호 확인</td>
                    <td>
                        <input type="password" name="pass2" placeholder="비밀번호 확인 입력"/>
                        <span class="resultPass"></span>
                    </td>
                </tr>
            </table>
            <table border="1">
                <caption>개인정보 입력</caption>
                <tr>
                    <td>이름</td>
                    <td>
                        <input type="text" name="name" placeholder="이름 입력"/>
						<span class="resultName"></span>
                    </td>
                </tr>
                <tr>
                    <td>별명</td>
                    <td>
                        <p>공백없이 한글, 영문, 숫자만 입력가능</p>
                        <input type="text" name="nick" placeholder="별명 입력"/>
                        <span class="resultNick"></span>                            
                    </td>
                </tr>
                <tr>
                    <td>E-Mail</td>
                    <td>
                       <input type="email" name="email" placeholder="이메일 입력"/>
                       <button type="button" id="btnSendEmail"><img src="../images/chk_auth.gif" alt="인증번호 받기"/></button>
                       <span class="resultEmail"></span>
                       <div class="auth">
                           <input type="text" name="auth" placeholder="인증번호 입력"/>
                           <button type="button" id="btnAuthEmail"><img src="../images/chk_confirm.gif" alt="확인"/></button>
                       </div>
                   </td>
                </tr>
                <tr>
                    <td>휴대폰</td>
                    <td>
                        <input type="text" name="hp" placeholder="- 포함 13자리 입력" minlength="13" maxlength="13" />
                    	<span class="resultHp"></span>
                    </td>
                </tr>
                <tr>
                    <td>주소</td>
                    <td>
                        <div>
                            <input type="text" name="zip" id="zip" placeholder="우편번호" readonly/>                                
                            <button type="button" class="btnZip" onclick="postcode()"><img src="../images/chk_post.gif" alt=""></button>
                        </div>                            
                        <div>
                            <input type="text" name="addr1" id="addr1" placeholder="주소를 검색하세요." readonly/>
                        </div>
                        <div>
                            <input type="text" name="addr2" id="addr2" placeholder="상세주소를 입력하세요."/>
                        </div>
                    </td>
                </tr>
            </table>

            <div>
                <a href="/jboard/user/login.do" class="btnCancel">취소</a>
                <input type="submit"   class="btnSubmit" value="회원가입"/>
            </div>    
        </form>
    </section>
</main>
<%@ include file="./_footer.jsp" %>