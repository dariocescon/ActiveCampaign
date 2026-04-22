# Skill: SpringEndpointGenerator

## Istruzioni
Genera endpoint REST completi.

## Requisiti
- Controller + Service + DTO + Repository
- Validazione
- ResponseEntity
- I metodi devono essere testabili
- Gestione degli errori
- Logga

## Esempio input
"crea endpoint ottenere i dettagli della Issue"

## Output atteso
@PostMapping("/")
@ResponseBody
public ResponseEntity<IssueDTO> getIssueDetails(@RequestParam(value = "id", required = true) String issueId) {
    try {
        IssueDTO dto = issuesService.getIssueDetails(issueId);
        return ResponseEntity.ok(dto);
    } catch (Exception e) {
        log.error("Error fetching issue details for {}: {}", issueId, e.getMessage(), e);
        return ResponseEntity.status(500).build();
    }
}