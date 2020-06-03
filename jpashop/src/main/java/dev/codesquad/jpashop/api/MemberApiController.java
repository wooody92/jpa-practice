package dev.codesquad.jpashop.api;

import dev.codesquad.jpashop.domain.Member;
import dev.codesquad.jpashop.service.MemberService;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

  private final MemberService memberService;

  @PostMapping("/api/v1/members")
  public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
    Long id = memberService.join(member);
    return new CreateMemberResponse(id);
  }

  @PostMapping("/api/v2/members")
  public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
    Member member = new Member();
    member.setName(request.getName());

    Long id = memberService.join(member);
    return new CreateMemberResponse(id);
  }

  @Data
  static class CreateMemberRequest {

    @NotEmpty
    private String name;

    public CreateMemberRequest(String name) {
      this.name = name;
    }
  }

  @Data
  static class CreateMemberResponse {

    private Long id;

    public CreateMemberResponse(Long id) {
      this.id = id;
    }
  }
}
