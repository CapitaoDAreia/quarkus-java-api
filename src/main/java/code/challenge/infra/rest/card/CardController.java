package code.challenge.infra.rest.card;

import code.challenge.app.exceptions.card.CardAlreadyExistsException;
import code.challenge.app.exceptions.card.CardNotFoundException;
import code.challenge.app.exceptions.card.CreateCardException;
import code.challenge.app.usecases.card.ActivateCardUseCase;
import code.challenge.app.usecases.card.CreateCardUseCase;
import code.challenge.infra.dtos.CreateCardDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/card")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CardController {
    private final CreateCardUseCase createCardUseCase;
    private final ActivateCardUseCase activateCardUseCase;

    public CardController(
            CreateCardUseCase createCardUseCase,
            ActivateCardUseCase activateCardUseCase
    ) {
        this.createCardUseCase = createCardUseCase;
        this.activateCardUseCase = activateCardUseCase;
    }

    @POST
    @Path("/create")
    @Transactional
    public Response createCard(
            @Valid CreateCardDTO createCardDTO
    ) {
        try {
            var useCaseInput = new CreateCardUseCase.Input(
                    createCardDTO.number(),
                    createCardDTO.expirationDate(),
                    createCardDTO.cvv(),
                    createCardDTO.country(),
                    createCardDTO.account()
            );

            var card = createCardUseCase.execute(useCaseInput);

            return Response.ok(card).build();
        } catch (CreateCardException | CardAlreadyExistsException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    
    @PATCH
    @Path("/activate/{cardNumber}")
    @Transactional
    public Response activateCard(
            @PathParam("cardNumber") String cardNumber
    ){
        try {
            var input = new ActivateCardUseCase.Input(cardNumber);
            var card = activateCardUseCase.execute(input);

            return Response.ok(card).build();
        } catch (CardNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
