class JsonProvider
  class << self
    def get_providers_details
      @providers_details ||= load_providers
    end

    private

    def load_providers
      providers_metadata = JsonParser.parse_file('providers/providers.json')

      @providers_details = providers_metadata.map do |provider_details|
        provider = Provider.new
        provider.parse_details(provider_details)
        provider
      end

      @providers_details
    end
  end
end
