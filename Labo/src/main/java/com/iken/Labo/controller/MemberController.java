package com.iken.Labo.controller;


import com.iken.Labo.model.Role;
import com.iken.Labo.repository.RoleRepository;
import com.iken.Labo.service.RoleService;
import org.springframework.web.bind.annotation.*;
import com.iken.Labo.model.Member;
import com.iken.Labo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public String listMembers(Model model) {
        List<Member> members = memberService.getAllMembers();
        model.addAttribute("members", members);
        return "member/list-member"; // Make sure your template name is correct
    }

    @GetMapping("/add")
    public String showAddMemberForm(Model model) {
        model.addAttribute("member", new Member());

        // Créez une variable locale pour stocker la liste des rôles
        List<Role> roles = roleService.getAllRoles();

        model.addAttribute("roles", roles); // Ajouter les rôles au modèle
        return "member/add-member";
    }


    @PostMapping("/add")
    public String addMember(@ModelAttribute("member") Member member, @RequestParam("role") String roleId,Model model) {

        String roleName = "role_name";
        Role role = roleRepository.findById(roleId).orElse(null);

        if (role == null) {
            // Handle the case where the role doesn't exist
            // You can use redirectAttributes to pass an error message
            model.addAttribute("erroes","role not found");
            return "redirect:/members/add";
        }
        // Set the member's roles here if necessary
        member.setRoles(Collections.singletonList(role));

        memberService.save(member);
        return "redirect:/members/list";
    }



    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Member member = memberService.getMemberById(id);
        if (member != null) {
            List<Role> roles = roleService.getAllRoles();
            model.addAttribute("member", member);
            model.addAttribute("roles", roles);
            return "member/edit-member";
        } else {
            model.addAttribute("error", "Member not found");
            return "redirect:/members/list";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateMember(@PathVariable("id") Long id, @ModelAttribute("member") Member updatedMember,
                               @RequestParam("roleId") String roleId, Model model) {
        Member memberToUpdate = memberService.getMemberById(id);
        Role role = roleRepository.findById(roleId).orElse(null);

        if (memberToUpdate != null && role != null) {
            memberToUpdate.setFirstName(updatedMember.getFirstName());
            memberToUpdate.setLastName(updatedMember.getLastName());
            memberToUpdate.setUsername(updatedMember.getUsername());
            memberToUpdate.setEmail(updatedMember.getEmail());
            memberToUpdate.setPassword(updatedMember.getPassword());
            memberToUpdate.setPhone(updatedMember.getPhone());
            memberToUpdate.getRoles().add(role);

            memberService.save(memberToUpdate);
            return "redirect:/members/list";
        } else {

            model.addAttribute("error", "Member or role not found");
            return "redirect:/members/list";
        }
    }




    @GetMapping("/delete/{id}")
    public String deleteMember(@PathVariable("id") Long id, Model model) {
        Member memberToDelete = memberService.getMemberById(id);

        if (memberToDelete != null) {
            memberService.delete(memberToDelete.getId());
            return "redirect:/members/list";
        } else {

            model.addAttribute("error", "Member not found");
            return "redirect:/members/list";
        }
    }

    @PostMapping("/removeRole/{memberId}/{roleId}")
    public String removeRoleFromMember(@PathVariable("memberId") Long memberId, @PathVariable("roleId") String roleId, Model model) {
        Member member = memberService.getMemberById(memberId);
        Role removerole = member.getRoles().stream().filter(role -> role.getId_role().equals(roleId)).findFirst().orElse(null);
        if (member == null ) {
            // Check if the member has the role before removing it
            return "redirect:/members/list";
        }
        member.getRoles().remove(removerole);
        memberService.saveMember(member);

        return "redirect:/members/list";
    }



}
