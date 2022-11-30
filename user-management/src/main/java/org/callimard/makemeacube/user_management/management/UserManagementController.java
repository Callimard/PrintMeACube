package org.callimard.makemeacube.user_management.management;

import lombok.RequiredArgsConstructor;
import org.callimard.makemeacube.common.api.ApiV1;
import org.callimard.makemeacube.jwt.aop.RequiresJwtAuthentication;
import org.callimard.makemeacube.models.dto.UserDTO;
import org.callimard.makemeacube.models.sql.RegistrationProvider;
import org.callimard.makemeacube.security.aop.PersonalAuthorisation;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(ApiV1.USERS_URL)
public class UserManagementController {

    // Variables.

    private final UserManagementService userManagementService;

    // Methods.

    @PostMapping("/basic-registration")
    public UserDTO basicUserRegistration(@RequestBody UserManagementService.BasicUserRegistrationDTO basicUserRegistrationDTO) {
        return userManagementService.basicUserRegistration(basicUserRegistrationDTO, RegistrationProvider.LOCAL).toDTO();
    }

    @PostMapping("/maker-registration")
    public UserDTO makerUserRegistration(@RequestBody UserManagementService.MakerUserRegistrationDTO makerUserRegistrationDTO) {
        return userManagementService.makerUserRegistration(makerUserRegistrationDTO, RegistrationProvider.LOCAL).toDTO();
    }

    @RequiresJwtAuthentication
    @PersonalAuthorisation
    @GetMapping("/{userId}")
    public UserDTO getUserInformation(@PathVariable(name = "userId") Integer userId) {
        return userManagementService.getUser(userId).toDTO();
    }

    @RequiresJwtAuthentication
    @PersonalAuthorisation
    @PutMapping("/{userId}")
    public UserDTO updateUserInformation(@PathVariable(name = "userId") Integer userId,
                                         @RequestBody UserManagementService.UserUpdatedInformation userUpdatedInformation) {
        return userManagementService.updateUserInformation(userId, userUpdatedInformation).toDTO();
    }

    @RequiresJwtAuthentication
    @PersonalAuthorisation
    @PostMapping("/{userId}/addresses")
    public UserDTO addUserAddresses(@PathVariable(name = "userId") Integer userId,
                                    @RequestBody UserManagementService.UserAddressInformationDTO userAddressInformationDTO) {
        return userManagementService.addUserAddress(userId, userAddressInformationDTO).toDTO();
    }

    @RequiresJwtAuthentication
    @PersonalAuthorisation
    @DeleteMapping("/{userId}/addresses/{userAddressId}")
    public UserDTO deleteUserAddresses(@PathVariable(name = "userId") Integer userId,
                                       @PathVariable(name = "userAddressId") Integer userAddressId) {
        return userManagementService.deleteUserAddress(userId, userAddressId).toDTO();
    }

    @RequiresJwtAuthentication
    @PersonalAuthorisation
    @PostMapping("/{userId}/maker-tools/printer3ds")
    public UserDTO addPrinter3D(@PathVariable(name = "userId") Integer userId,
                                @RequestBody UserManagementService.Print3DInformationDTO print3DInformationDTO) {
        return userManagementService.addPrinter3D(userId, print3DInformationDTO).toDTO();
    }

    @RequiresJwtAuthentication
    @PersonalAuthorisation
    @DeleteMapping("/{userId}/maker-tools/{makerToolId}")
    public UserDTO deleteMakerTool(@PathVariable(name = "userId") Integer userId,
                                   @PathVariable(name = "makerToolId") Integer makerToolId) {
        return userManagementService.deleteMakerTool(userId, makerToolId).toDTO();
    }
}
