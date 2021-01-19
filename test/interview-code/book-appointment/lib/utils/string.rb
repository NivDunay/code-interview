module Utils
  class String
    def self.is_number?(str)
      begin
        Integer(str)
      rescue ArgumentError
        return false
      end
      true
    end
  end
end
