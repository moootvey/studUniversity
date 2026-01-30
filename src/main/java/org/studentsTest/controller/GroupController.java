package org.studentsTest.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.studentsTest.dto.request.AddGroupRequestDto;
import org.studentsTest.dto.response.AddGroupResponseDto;
import org.studentsTest.dto.response.GetGroupResponseDto;
import org.studentsTest.service.interfaces.GroupService;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @GetMapping("/")
    public String getGroupsView(Model model) {
        List<GetGroupResponseDto> groups = groupService.getAllGroups(0);
        model.addAttribute("groups", groups);
        model.addAttribute("totalPages", groupService.getTotalGroupsPages());
        model.addAttribute("currentPage", 1);
        return "groups";
    }

    @GetMapping("/getGroups")
    public ResponseEntity<List<GetGroupResponseDto>> getGroupsList(@RequestParam(name = "page", defaultValue = "0") Integer page) {
        // Это для вывода остальных 10 групп
        return ResponseEntity.ok().body(groupService.getAllGroups(page));
    }

    @PostMapping("/addGroup")
    public ResponseEntity<AddGroupResponseDto> addGroup(@Valid @RequestBody AddGroupRequestDto addGroupRequestDto,
                                                        UriComponentsBuilder uriComponentsBuilder) {
        URI location = uriComponentsBuilder.path("/groups/addGroup").build().toUri();
        return ResponseEntity.created(location).body(groupService.addGroup(addGroupRequestDto));
    }
}
